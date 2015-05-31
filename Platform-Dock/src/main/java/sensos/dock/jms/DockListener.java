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
package sensos.dock.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import sensos.contracts.jms.JmsConstants;
import sensos.contracts.messaging.PayloadResponse;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.PlatformMessageResponse;
import sensos.contracts.messaging.v1.JsonRegisterResponse;

@MessageDriven(mappedName = JmsConstants.QUEUE_DOCK, activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue")
    })
public class DockListener implements MessageListener {

    private static Logger l = Logger.getLogger(DockListener.class.getName());

   

    public DockListener() {
    }
    
    /**
     * 
     */
    @PostConstruct
    public void init() {

        l.log(Level.INFO, "Initializing {0}...", DockListener.class.getName());
        
        l.log(Level.INFO, "Initialized {0}...", DockListener.class.getName());

    }    

    /**
     * 
     */
    @PreDestroy
    public void destroy() {

        l.log(Level.INFO, "Closing {0}...", DockListener.class.getName());

        l.log(Level.INFO, "Closed {0}...", DockListener.class.getName());

    }

    /**
     * 
     * @throws Throwable 
     */
    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {

        super.finalize();

    }    
    
    /**
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {

        if (message == null) {
            return;
        }

        ObjectMessage objectMessage = (ObjectMessage) message;

        Object o = null;

        try {
            o = objectMessage.getObject();
        } catch (JMSException ex) {
            Logger.getLogger(DockListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        // On responses execute following
        //
        if (o instanceof PlatformMessageResponse) {

            PlatformMessageResponse msg = (PlatformMessageResponse) o;

            PayloadResponse payload = msg.getPayload();

            if (payload instanceof JsonRegisterResponse) {


            }

        } 

        //
        // On requests execute following
        //
        else {

            PlatformMessageRequest msg = (PlatformMessageRequest) o;

        }

    }

    /**
     * 
     * @param platformRequest
     * @return
     * @throws InterruptedException 
     */
    public static PlatformMessageResponse wait(PlatformMessageRequest platformRequest) throws InterruptedException {
        return null;
    }

}
