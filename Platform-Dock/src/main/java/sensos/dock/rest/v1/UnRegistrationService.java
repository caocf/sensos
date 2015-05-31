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
import sensos.bo.device.Device;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.v1.JsonUnregisterRequest;
import sensos.contracts.messaging.v1.JsonUnregisterResponse;
import sensos.contracts.messaging.v1.JsonUnregisterResponseElement;
import sensos.dock.rest.RequestReader;

/**
 *
 * @author sensos
 */
@Singleton
@Path("unregister")
public class UnRegistrationService {

    DockAuthenticator authenticator = new DockAuthenticator();
    private Logger l = Logger.getLogger(this.getClass().getName());
    private RequestReader r = new RequestReader();

    /**
     * Unregisters the device from the Platform.
     *
     * <code>
     *
     * Example of the request message:
     * {
     * "AccessKey": { "id": "?DEVICEID", "key": "?DEVICEKEY" }
     *
     * "Request": { "method": "unregister", "requireAck": "true|false" } }
     *
     * Description of the fields:
     *
     * ?ID Valid access key id that has has been acquired from the platform.
     * ?KEY Valid corresponding security token that the platform has issued.
     *
     * Example of response message
     *
     * {
     * "Response": { "method": "unregister", "status": "success|fail" } }
     *
     * </code>
     *
     * @param request JSON-message basing on message example above.
     * @return 
     * @throws sensos.dock.rest.v1.ServiceException
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonUnregisterResponse unregister(@Context HttpServletRequest request) throws ServiceException {

        l.log(Level.INFO, "Unregister invoked.");

        //Initialize
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonUnregisterRequest requestPayload = null;
        JsonUnregisterResponse responsePayload = null;
        PlatformMessageRequest messageRequest = null;
        
        //
        //Read
        //
        StringBuilder sb = r.readRequest(request);

        //
        //Process
        //
        try {
            requestPayload = objectMapper.readValue(new StringReader(sb.toString()), JsonUnregisterRequest.class);
        } catch (IOException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        // Authenticate
        //
        Device device = authenticator.authenticate(requestPayload);

        //
        // Send request and dont wait for an answer.
        //
        messageRequest = new PlatformMessageRequest( requestPayload );
        messageRequest.setDeviceId( device.getId() );

        //
        // Send Message
        //
        //try {
        //    SOADispatcher2.send(platformRequest);
        //} catch (SOAException ex) {
        //    Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
        responsePayload = new JsonUnregisterResponse();
        JsonUnregisterResponseElement unregisterResponseElement = new JsonUnregisterResponseElement();
        unregisterResponseElement.setMethod("unregister");
        unregisterResponseElement.setStatus("success");
        responsePayload.setResponse(unregisterResponseElement);
        
        return responsePayload;

    }

}