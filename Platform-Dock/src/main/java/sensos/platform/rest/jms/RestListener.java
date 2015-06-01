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
package sensos.platform.rest.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import sensos.contracts.business.IUserManager;
import sensos.contracts.messaging.PlatformMessageRequest;

@MessageDriven(mappedName = "platformRest", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode",
            propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class RestListener implements MessageListener {

    @EJB
    private IUserManager userManager;

    private RestLetterChannel letterChannel = new RestLetterChannel(60);

    private static Logger l = Logger.getLogger(RestListener.class.getName());

    //private AkkaFactory akkaFactory;

    public RestListener() {

      //   akkaFactory = new AkkaFactory(userManager);

    }

    /**
     *
     */
    @PostConstruct
    public void init() {

        l.log(Level.INFO, "Initializing {0}...", RestListener.class.getName());

        l.log(Level.INFO, "Initialized {0}...", RestListener.class.getName());

    }

    /**
     *
     */
    @PreDestroy
    public void destroy() {

        l.log(Level.INFO, "Closing {0}...", RestListener.class.getName());

        //AkkaFactory.terminate();

        l.log(Level.INFO, "Closed {0}...", RestListener.class.getName());

    }

    /**
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {

        super.finalize();

    }

    /**
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {

        l.log(Level.INFO, "Received {0}...", RestListener.class.getName());

        if (message == null) {
            return;
        }

        ObjectMessage objectMessage = (ObjectMessage) message;

        //
        // Unwrap the message
        //
        Object o = null;

        try {
            o = objectMessage.getObject();
        } catch (JMSException ex) {
            Logger.getLogger(RestListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        PlatformMessageRequest r = null;

        try {
            r = (PlatformMessageRequest) o;
        } catch (Exception e) {
            Logger.getLogger(RestListener.class.getName()).log(Level.SEVERE, null, e);
            return;
        }

        //
        // Push the message to AkkaFactory
        //
        //akkaFactory.tell(r);

    }

}
