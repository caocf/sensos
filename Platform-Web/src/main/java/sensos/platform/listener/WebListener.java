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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import sensos.contracts.jms.JmsConstants;

@MessageDriven(mappedName = JmsConstants.QUEUE_WEB, activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                                  propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                                  propertyValue = "javax.jms.Queue")
    })
public class WebListener implements MessageListener {

    public WebListener() { }
    
    private static Logger l = Logger.getLogger(WebListener.class.getName());

    @PostConstruct
    public void init() {

        l.log(Level.INFO, "Initializing {0}...", WebListener.class.getName());
        
        l.log(Level.INFO, "Initialized {0}...", WebListener.class.getName());

    }

    @PreDestroy
    public void destroy() {

        l.log(Level.INFO, "Closing {0}...", WebListener.class.getName());
        
        l.log(Level.INFO, "Closed {0}...", WebListener.class.getName());


    }

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

        l.log(Level.INFO, "Received {0}...", WebListener.class.getName());
    }

}
