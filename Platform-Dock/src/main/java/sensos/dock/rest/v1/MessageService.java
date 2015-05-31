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
import sensos.contracts.messaging.v1.JsonMessageRequest;
import sensos.contracts.messaging.v1.JsonMessageResponse;
import sensos.dock.rest.RequestReader;

/**
 *
 * @author sensos
 */
@Singleton
@Path("send")
public class MessageService {

    DockAuthenticator authenticator = new DockAuthenticator();
    protected Logger l = Logger.getLogger(this.getClass().getName());
    private final RequestReader requestReader = new RequestReader();

    public MessageService() { }

    /**
     * Send data from the Device to the Platform. - This method is asynchronous.
     *
     * Example of the request message:
     *
     * {
     * "AccessKey": { "id": "?ID", "key": "?KEY" }, "Request": { "method":
     * "data", "messageId": "?MESSAGEID", "timestamp": "?TIMESTAMP",
     * "requireAck": "true|false", "JsonDataSetElement": [ { "Data": { "type":
     * "?TYPE", "encoding": "?ENCODING", "data": "?DATA" } } ] }
     *
     * Description of the fields: ?ID Valid access key id that has has been
     * acquired from the platform. ?KEY Valid corresponding security token that
     * the platform has issued. ?MESSAGEID Device generated message-id. It is
     * used by the device to handle callback-messages appropriately. ?TIMESTAMP
     * Timestamp in Julian date format ?TYPE Type of incoming data. Different
     * types are documented in different document. Valid types are, for example:
     * - 'temperature', - 'accelometer', - 'video-stream' ?ENCODING Encoding
     * format of the data. ?DATA The actual data. Data MUST be constructed
     * according to the type definition.
     *
     * - Message can contain multiple Data-objects. - If data type is not
     * present, that portion of data will not be processed. - If data of the
     * portion is not valid (according to type definition), that portion of data
     * will not be processed. - If user requests acknowledge (ack), user must be
     * subscribed also to WebSocket-channel, otherwise ack will not be
     * delivered.
     *
     * Example of the response message:
     *
     * {
     * "Response": { "method": "message", "status": "success|fail" } }
     *
     * @param request
     * @return
     * @throws sensos.dock.rest.v1.ServiceException     
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonMessageResponse send(@Context HttpServletRequest request) throws ServiceException {

        l.log(Level.INFO, "Send invoked.");

        //
        // Initialize
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonMessageRequest  requestPayload = null;
        JsonMessageResponse responseObject = new JsonMessageResponse();

        //
        // Read
        //
        StringBuilder sb = requestReader.readRequest(request);

        //
        // Process
        //
        try {
            requestPayload = objectMapper.readValue(new StringReader(sb.toString()), JsonMessageRequest.class);
        } catch (IOException ex) {
            throw new ServiceException(ex);
        }

        //
        // Authenticate
        //
        Device d = authenticator.authenticate(requestPayload);
        
        //
        // Send Message
        //
        PlatformMessageRequest messageRequest = new PlatformMessageRequest( requestPayload );
        
        messageRequest.setDeviceId(d.getId());
        messageRequest.setDeviceOwnerId(d.getOwner());
        
        //
        // Send Message
        //
        //try {
        //    SOADispatcher2.send(platformRequest);
        //} catch (SOAException ex) {
        //    Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
        responseObject.getResponse().setStatus("success");

        return responseObject;

    }

}
