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
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sensos.contracts.jms.JmsConstants;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.PlatformMessageResponse;

/**
 *
 * @author sensos
 */
@Singleton
@LocalBean
@Startup
public class RestDispatcher {

    private static InitialContext jndi;
    private static Logger l = Logger.getLogger(RestDispatcher.class.getName());
    private static QueueConnection connection;
    private static QueueSession session;
    private static QueueSender sender;
    private static boolean isInitialized;

    @Resource(name = "SensosJmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "platformRest")
    private Queue queue;

    @PostConstruct
    void init() {

        l.log(Level.INFO, "Initializing {0}...", RestDispatcher.class.getName());

        try {

            jndi = new InitialContext();
            connection = (QueueConnection) connectionFactory.createConnection();
            session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);
            connection.start();

            isInitialized = true;

        } catch (NamingException | JMSException ex) {
            Logger.getLogger(RestDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    public void destroy() {

        l.log(Level.INFO, "Closing {0}...", RestDispatcher.class.getName());

        try {

            //Close connections.
            sender.close();
            session.close();
            connection.close();

        } catch (JMSException ex) {

            l.log(Level.SEVERE, "Close error occured {0}...", ex.getMessage());

        }

    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {

        destroy();

        super.finalize();

    }

    /**
     * Method to send requests.
     *
     * @param message
     * @throws SOAException
     */
    public static void send(PlatformMessageRequest message) {

        l.log(Level.INFO, "{0} sending...", RestDispatcher.class.getName());

        ObjectMessage objectMessage = null;

        try {

            objectMessage = session.createObjectMessage();
            objectMessage.setObject(message);
            sender.send(objectMessage);

        } catch (JMSException ex) {

            System.out.print(ex);
            

        }

        l.log(Level.INFO, "{0} sent...", RestDispatcher.class.getName());

    }

    /**
     * Method to send responses.
     *
     * @param message
     * @throws SOAException
     */
    public static void send(PlatformMessageResponse message) {

        l.log(Level.INFO, "{0} sending...", RestDispatcher.class.getName());

        ObjectMessage objectMessage = null;

        try {

            objectMessage = session.createObjectMessage();
            objectMessage.setObject(message);
            sender.send(objectMessage);

        } catch (JMSException ex) {

            System.out.print(ex);

        }

    }

}
