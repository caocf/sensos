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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ws.rs.*;
import sensos.contracts.messaging.v1.JsonPingRequest;
import sensos.contracts.messaging.v1.JsonPingResponse;
import sensos.dock.rest.RequestReader;

/**
 *
 * @link https://jersey.java.net/documentation/2.6/user-guide.html
 * @author sensos
 */

@Singleton
@Path("ping")
public class PingService implements PingServiceMBean  {
    
    protected Logger l = Logger.getLogger(this.getClass().getName());
    
    public PingService() { }
    
    
    /**
     * Execute ping.
     * 
     * Example of the request:
     * {
     * "Request":   {
     *                  "method":     "ping"
     *              }
     * }
     * Example of the response:
     * 
     * {
     * "Response":  {
     *                  "method":       "ping",
     *                  "status":       "success|fail"
     *              }
     *  }
     * 
     */
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public JsonPingResponse ping(String response) throws ServiceException {
        
        l.log(Level.INFO, "Ping invoked.");
        
        //
        //Initialize
        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JsonPingRequest requestObject = null;
        JsonPingResponse    responseObject = null;
        
        //
        //Process        
        //
        try {
            requestObject = objectMapper.readValue(response, JsonPingRequest.class);
        } catch (IOException ex) {
            Logger.getLogger(PingService.class.getName()).log(Level.SEVERE, null, ex);
        }

        responseObject = new JsonPingResponse();
        
        if( requestObject == null ) {
        
            responseObject.getResponse().setStatus("fail");
            
        } else {
            
            responseObject.getResponse().setStatus("success");
            
        }
            
        return responseObject;
        
    }
    
}