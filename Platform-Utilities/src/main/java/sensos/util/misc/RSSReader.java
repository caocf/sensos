/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sensos.util.misc;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.misc.BASE64Encoder;

public class RSSReader {

    boolean requireAuth;
    String url, username, password = "";
    
    private static RSSReader instance = null;

    private RSSReader(String url)  { 
        this.url=url;
    }
    
    private RSSReader(String url, String... p) {
        this.url=url;
        this.username=p[0];
        this.password=p[1];
        requireAuth=true;
    } 

    public static RSSReader getInstance(String url, String... p) {
        if (instance == null) {
            
            if(p==null)
                instance = new RSSReader(url);
            else
                instance = new RSSReader(url, p);
            
        }
        return instance;
    }
    /**
     * 
     * @return 
     */
    public static RSSReader getInstance() {
        if (instance==null)
            return null;
        else
            return instance;
    }
    
    public class News {
        String title,link, publishDate,author,comments,description;

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title=title;
        }
        
        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }
        
    }
    

    public ArrayList<News> writeNewsIntoArray() {
        ArrayList<News> news = new ArrayList<News>();
        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL u = new URL(url);

            URLConnection uc = u.openConnection();
            
            if(requireAuth) {
            
                String userpass = username + ":" + password;
                String basicAuth = "Basic " + new String(new BASE64Encoder().encode(userpass.getBytes()));
                uc.setRequestProperty ("Authorization", basicAuth);
            
            }

            Document doc = builder.parse(uc.getInputStream());

            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);
                
                News n = new News();
                n.setTitle(  getElementValue(element, "title") );
                n.setAuthor( getElementValue(element, "dc:creator") );
                n.setComments( getElementValue(element, "wfw:comment") );
                n.setDescription( getElementValue(element, "description") );
                n.setLink( getElementValue(element, "link") );
                n.setPublishDate( getElementValue(element, "pubDate") );
                
                news.add(n);

            }//for
        }//try
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return news;
    }
    
    public void writeNews() {
        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL u = new URL(url);

            URLConnection uc = u.openConnection();
            
            if(requireAuth) {
            
                String userpass = username + ":" + password;
                String basicAuth = "Basic " + new BASE64Encoder().encode(userpass.getBytes());
                uc.setRequestProperty ("Authorization", basicAuth);
            
            }

            Document doc = builder.parse(uc.getInputStream());

            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);
                System.out.println("Title: " + getElementValue(element, "title"));

                System.out.println("Link: " + getElementValue(element, "link"));
                System.out.println("Publish Date: " + getElementValue(element, "pubDate"));
                System.out.println("author: " + getElementValue(element, "dc:creator"));
                System.out.println("comments: " + getElementValue(element, "wfw:comment"));
                System.out.println("description: " + getElementValue(element, "description"));
                System.out.println();
            }//for
        }//try
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch (Exception ex) {
        }
        return "";
    } //private String getCharacterDataFromElement

    protected float getFloat(String value) {
        if (value != null && !value.equals("")) {
            return Float.parseFloat(value);
        }

        return 0;
    }

    protected String getElementValue(Element parent, String label) {
        return getCharacterDataFromElement((Element) parent.getElementsByTagName(label).item(0));
    }

    public static void main(String[] args) {
        
        String url = "http://sensos.fi/?feed=rss2";
        String username="sensos";
        String password="s3ns0sf1";
        
        RSSReader reader = RSSReader.getInstance(url,username,password);
        reader.writeNews();
        
        System.out.println("--------------------------------------");
        
        ArrayList<News> news = reader.writeNewsIntoArray();
        
        for(News n : news) {
                System.out.println("Link: " + n.getLink());
                System.out.println("Publish Date: " + n.getPublishDate());
                System.out.println("author: " + n.getAuthor());
                System.out.println("comments: " + n.getComments());
                System.out.println("description: " + n.getDescription());
                System.out.println();
            
        }
        
        
    }
    
    
}