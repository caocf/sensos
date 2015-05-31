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
package sensos.business.manager;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import sensos.contracts.business.ITokenManager;

/**
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(ITokenManager.class)
//@DependsOn("Manager")
public class TokenManager implements ITokenManager {

    Thread cleanerThread;
    Cleaner cleaner;
    
    public TokenManager() {
        
        cleanerThread = new Thread( cleaner=new Cleaner(tokenList) );
        cleanerThread.start();
        
    }
    
    Set<Token> tokenList = new HashSet<Token> ();
    
    class Token {
        Date created = new Date();
        String token;
        int alive;

        Token(int s) {
        
            if(s==0 || s > 300)
                s=300;
            token = UUID.randomUUID().toString().replace("-", "");
            
        }

        public int getAlive() {
            return alive;
        }

        public Date getCreated() {
            return created;
        }

        public String getToken() {
            return token;
        }
        
        
        
    }
    
    @Override
    public String createTemporaryToken(int seconds) {
        Token t; 
        tokenList.add(t = new Token(seconds));
        return t.getToken();
    }

    @Override
    public boolean removeTemporaryToken(String token) {
        return tokenList.remove(token);
    }
    
    class Cleaner implements Runnable {

        Thread sleeper = new Thread();
        Set<Token> tokenSet;
        boolean stop=false;
        
        public Cleaner( Set<Token> tokenSet ) {
            this.tokenSet=tokenSet;
        }
        
        
        public void stop() {
            stop=true;
        }
        
        @Override
        public void run() {
            
            ArrayList<Token> removables = new ArrayList<>();
            
            while(true) {
                
                long currentTime = System.currentTimeMillis();
                
                synchronized( tokenSet ) {
                
                    for(Token t : tokenSet) {
                        
                        if((( currentTime - t.getCreated().getTime() ) / 1000) > t.getAlive()) {
                           removables.add(t); 
                        }
                        
                    }
                    
                    for(Token t : removables) {
                        
                        tokenSet.remove(t);
                        
                    }
                    
                    removables.clear();
                    
                    if(stop)
                        break;
                    
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TokenManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }
 
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        cleaner.stop();
    }
    
}
