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
package sensos.dock.rest.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.PlatformMessageResponse;
import sensos.dock.jms.DockListener;
import sensos.contracts.messaging.v1.JsonRegisterRequest;
import sensos.contracts.messaging.v1.JsonRegisterResponse;

import sensos.dock.rest.RequestReader;

/**
 *
 * @author sensos
 */
@Singleton
@Path("register")
public class RegistrationService {

    private Logger l = Logger.getLogger(this.getClass().getName());
    private final RequestReader r = new RequestReader();

    public RegistrationService() {
    }

    /**
     * Registers the devices to the Platform. This method is not asynchronous.
     *
     * Within this method:
     *
     * - Platform starts to seek Devices owner by IP. - Platform seeks if user
     * with such an IP is logged to the system. - Platform checks whether that
     * User is currently executing Pairing. - Platform asks from user if this
     * device can be paired. - User approves the pairing. - Platform returns
     * answer according to the response message.
     *
     * Example of the request message:
     *
     * { "Request": { "method": "register" } }
     *
     *
     *
     *
     * Example of the response message:
     *
     * {
     *
     * "Response": { "method": "register|unregister", "status": "success|fail",
     * "statusCode": "code", "statusMessage":'message"
     *
     * "JsonAccessKeyElement": { "id": "id", "key": "key" } }
     *
     * }
     *
     * - Response message will contain an 'id' and 'key' that the Device must
     * use when executing other communications with the platform.
     *
     * @param request
     * @return 
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonRegisterResponse register(@Context HttpServletRequest request) throws ServiceException {

        l.log(Level.INFO, "Register invoked.");

        //
        //Initialize
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonRegisterRequest  requestObject = null;
        JsonRegisterResponse responseObject = null;

        //
        //Read
        //
        StringBuilder sb = new StringBuilder(r.readRequest(request));

        //
        //Process
        //
        try {
            requestObject = objectMapper.readValue(new StringReader(sb.toString()), JsonRegisterRequest.class);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        // Executes a register. The functionality waits for a certain moment nuntil it receives a callback from a callback channel.
        //
        requestObject.setRemoteAddr( request.getRemoteAddr() );
        PlatformMessageRequest platformRequest = new PlatformMessageRequest(requestObject);

        //
        // Send Message
        //
        //try {
        //    SOADispatcher2.send(platformRequest);
        //} catch (SOAException ex) {
        //    Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        //}

        //
        // Wait for answer for a specific amount of time.
        //
        PlatformMessageResponse platformResponse = null;

        try {
            platformResponse = DockListener.wait(platformRequest);
        } catch (InterruptedException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(ex.getMessage());
        }
        
        return (JsonRegisterResponse) platformResponse.getPayload();

    }

}