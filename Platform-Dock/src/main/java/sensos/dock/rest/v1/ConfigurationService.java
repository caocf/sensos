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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.PlatformMessageResponse;
import sensos.contracts.messaging.v1.JsonConfigurationRequest;
import sensos.contracts.messaging.v1.JsonConfigurationResponse;
import sensos.dock.jms.DockListener;
import sensos.dock.rest.RequestReader;

/**
 * 
 * @author sensos
 */
@Path("configure")
public class ConfigurationService {

    protected Logger l = Logger.getLogger(this.getClass().getName());
    private RequestReader r = new RequestReader();
    DockAuthenticator authenticator = new DockAuthenticator();
    
    public ConfigurationService() { }

    /**
     * Configures a Device.
     * - This method is not asynchronous.
     * - A valid access key is required to run the request.
     * - This request inquiries parameters from the Platform by defining one or more 'param' elements.
     * 
     * Example request message:
     * 
     *  {
     *      "AccessKey": {
     *          "id": "ID",
     *          "key": "KEY"
     *      },
     *      "Request": {
     *          "method": "configure",
     *          "Config": [
     *              {
     *                  "param": "?PARAM_NAME"
     *              }, ...
     *          ]
     *      }
     *  }
     * 
     * Description of the fields:
     * ?ID              Valid access key id that has has been acquired from the platform.
     * ?KEY             Valid corresponding security token that the platform has issued.
     * ?PARAM_NAME      Valid parameter name the Device is asking. These parameters are defined 
     *                  as a part of the device configuration in the platform.
     * 
     * Example response message: 
     *  {
     * "Response":  {
     *                  "status":       "success|fail",
     *                  "statusCode":   "code",
     *                  "statusMessage":"message",
     *                  "method":       "configure",
     *                  "Config": [ {
     *                      "?PARAM_NAME": "?PARAM_VALUE", ...
     *                              }, ...
     *                            ]
     *              }
     *  }
     * 
     * Description of the fields:
     * ?PARAM_NAME      Parameter that the device is asking.
     * ?PARAM_VALUE     Value of asked parameter in UTF-8-format.
     * 
     * @param request JSON request that is represented in this document.
     * @return        JSON response that is represented in this document.
     * @throws sensos.dock.rest.v1.ServiceException
     */
    @POST
    @Consumes("application/json")
    public JsonConfigurationResponse configure(@Context HttpServletRequest request) throws ServiceException {
        
        l.log(Level.INFO, "Configuration invoked.");

        //
        // Initialize
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonConfigurationRequest     configurationRequest = null;
        JsonConfigurationResponse    responseObject = null;
        
        //
        // Read
        //
        StringBuilder sb = r.readRequest(request);
        
        //
        // Process
        //
        try {
            configurationRequest = objectMapper.readValue(new StringReader(sb.toString()), JsonConfigurationRequest.class);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        PlatformMessageRequest platformRequest = new PlatformMessageRequest(configurationRequest);
        
        //
        // Authenticate
        //
        authenticator.authenticate(configurationRequest);
        
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

        responseObject = (JsonConfigurationResponse) platformResponse.getPayload();        

        return responseObject;

    }

}