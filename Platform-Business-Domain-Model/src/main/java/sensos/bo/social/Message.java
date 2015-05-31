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
package sensos.bo.social;

import sensos.bo.social.Actor;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.social.Content;

/**
 *
 * @author joeri
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Message extends Content {

    private static final long serialVersionUID = 1L;

    private String network;
    private String remoteId;
    private String uri;
    private List<String> media = new LinkedList<String>();

    public Actor getActor() {
        return (Actor) super.getAuthor();
    }

    public void setActor(Actor author) {
        super.setAuthor(author);
    }

    @Override
    @XmlElement
    public String getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Override
    @XmlElement
    public long getCreationDate() {
        return super.getCreationDate();
    }

    @Override
    public void setCreationDate(long creationDate) {
        super.setCreationDate(creationDate);
    }

    @Override
    @XmlElement
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    @Override
    @XmlElement
    public String getSummary() {
        return super.getSummary();
    }

    @Override
    public void setSummary(String summary) {
        super.setSummary(summary);
    }

    @Override
    @XmlElement
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the media
     */
    @XmlElement
    public List<String> getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(List<String> media) {
        this.media = media;
    }
}
