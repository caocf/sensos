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
package sensos.platform.rest.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import sensos.bo.user.PlatformUser;
import sensos.contracts.messaging.v1.ActorOnlineStatusRequest;
//import sensos.platform.rest.jms.AkkaFactory;
import sensos.platform.rest.controller.RestControllerConstants;

/**
 *
 * @author sensos
 */
@WebListener
public class SessionListener implements HttpSessionAttributeListener {

    private static Logger l = Logger.getLogger(SessionListener.class.getName());

    public SessionListener() { }

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getName().equals(RestControllerConstants.ATTR_USER)) {

            PlatformUser user = (PlatformUser) event.getSession().getAttribute(RestControllerConstants.ATTR_USER);

            if (user != null) {

                l.log(Level.INFO, "Adding user into a logged Users.");

                ActorOnlineStatusRequest o = new ActorOnlineStatusRequest();
                o.setStatus(ActorOnlineStatusRequest.Status.Online);

                //AkkaFactory.putOnlineStatus(user, o);

            }

        }

    }

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        if (event.getName().equals(RestControllerConstants.ATTR_USER)) {

            PlatformUser user = (PlatformUser) event.getSession().getAttribute(RestControllerConstants.ATTR_USER);

            if (user != null) {

                l.log(Level.INFO, "Session destroyed.. removing user");

                ActorOnlineStatusRequest o = new ActorOnlineStatusRequest();
                o.setStatus(ActorOnlineStatusRequest.Status.Online);

                //AkkaFactory.putOnlineStatus(user, o);

            }

        }

    }

    /**
     * 
     * @param event 
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        if (event.getName().equals(RestControllerConstants.ATTR_USER)) {

            throw new SecurityException("User security violated.");

        }

    }

}
