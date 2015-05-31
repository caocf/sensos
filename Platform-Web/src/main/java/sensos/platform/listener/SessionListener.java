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
package sensos.platform.listener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import sensos.bo.user.PlatformUser;

/**
 *
 * @author sensos
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static Logger l = Logger.getLogger(SessionListener.class.getName());
    private static final HashSet<PlatformUser> loggedUsers = new HashSet<>();
    
    public SessionListener() { }

    /**
     * Seek if a particular user is logged in.
     *
     * @param userId
     * @return
     */
    static boolean isUserLogged(PlatformUser u) {

        if(u==null) return false;

        return loggedUsers.contains( u );

    }
    /**
     * Seek if a particular user is logged in.
     * @param uid
     * @return 
     */
    static boolean isUserLogged(Long uid) {
        
        for (PlatformUser u : loggedUsers) {
            if (uid.equals(u.getId())) {
                return true;
            }
        }

        return false;
        
    }

    /**
     * Seek if there are users from this address. (This could be something more
     * efficient)
     *
     * @param remoteAddress
     * @return
     
    static boolean isThereUsersFromThisAddress(String remoteAddress) {

        if (remoteAddress == null) {
            throw new IllegalArgumentException("Incoming remoteAddress cannot be null.");
        }

        Iterator<PlatformUser> i = loggedUsers.iterator();

        while (i.hasNext()) {

            PlatformUser u = i.next();

            if (remoteAddress.equals(u.getIp_address())) {
                return true;
            }

        }

        return false;

    }*/

    /**
     * Performed when user logs in.
     *
     * @param user
     
    public void login(PlatformUser user) {
        // Do your business thing and then
        loggedUsers.add(user);

    }*/

    /**
     * Performed when user logs out.
     *
     * @param user
     
    public void logout(PlatformUser user) {

        // Do your business thing and then
        loggedUsers.remove(user);

    }*/

    /**
     * Record the fact that a session has been created.
     *
     * @param event The session event
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {

        l.log(Level.INFO, "Starting to create new session...");
        
        

    }

    /**
     * Record the fact that user has been destroyed.
     *
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        l.log(Level.INFO, "Session destroyed...");

    }

    /**
     * Executed when a new attribute is added into the session. Forks user into
     * "data stream" that his/her devices send. Deploys user's applications into
     * a particular application server instance.
     *
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals("user")) {

            PlatformUser user = (PlatformUser) event.getSession().getAttribute("user");

            if (user != null) {

                l.log(Level.INFO, "New Session created.. adding user");
                loggedUsers.add(user);

            }

        }

    }

    /**
     * Executed when user session ends and user information is removed from the
     * session.
     *
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        if (event.getName().equals("user")) {

            PlatformUser user = (PlatformUser) event.getSession().getAttribute("user");

            //Bucket.unfork(user);
            if (user != null) {
                l.log(Level.INFO, "Session destroyed.. removing user");
                loggedUsers.remove(user);
            }

        }

    }

    /**
     * Executed when user attribute is replaced in the session.
     *
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        //nothing
    }
}
