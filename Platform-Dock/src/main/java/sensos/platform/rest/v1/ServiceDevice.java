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
package sensos.platform.rest.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sensos.authentication.bo.PublicAccessKey;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IDeviceManager;
import sensos.contracts.business.IUserManager;
import sensos.contracts.messaging.PlatformMessageResponse;
import sensos.contracts.messaging.v1.JsonAccessKeyElement;
import sensos.contracts.messaging.v1.JsonListenResponse;
import sensos.contracts.messaging.v1.JsonRegisterRequest;
import sensos.contracts.messaging.v1.JsonRegisterResponse;
import sensos.contracts.messaging.v1.JsonRegisterResponseElement;
import sensos.platform.rest.RequestReader;
import sensos.platform.rest.jms.RestLetterChannel;

/**
 *
 * @author joeri
 */
@ManagedBean
@Path("/device")
public class ServiceDevice {

    final RequestReader r = new RequestReader();

    public ServiceDevice() {
    }
    protected Logger l = Logger.getLogger(this.getClass().getName());
    @EJB
    protected IUserManager userManager;
    @EJB
    protected IDeviceManager deviceManager;
    protected InitialContext ic;

    @Context
    protected ServletContext context;

    @Context
    private UriInfo ucontext;

    @PostConstruct
    public void init() {
    }

    @POST
    @Path("listen")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonListenResponse listen(@Context HttpServletRequest request) throws ServiceException {

        l.log(Level.INFO, "{0} invoked.", ServiceDevice.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        final String remoteAddr = request.getRemoteAddr();
        JsonListenResponse responseObject = new JsonListenResponse();
        responseObject.setStatus("n");

        //
        // Peek is there any devices waiting for registration.
        //
        JsonRegisterRequest registerRequest = RestLetterChannel.peekRegisterRequest(remoteAddr);

        if (registerRequest == null) {
            return responseObject;
        }

        //
        // Device has been found. Render appropriate device information.
        //
        responseObject.setStatus("y");
        responseObject.setUsername("not defined");
        responseObject.setDescription("not defined");

        return responseObject;

    }

    /**
     *
     * @param request
     * @return
     */
    @POST
    @Path("accept")
    public void accept(@Context HttpServletRequest request) throws ServiceException {

        //
        // Execute binding and provide accessKey
        //
        PlatformUser user = (PlatformUser) request.getSession().getAttribute("user");
        PublicAccessKey accessKey = deviceManager.createDevice(user);

        //
        // Take the waiting request from the LetterChannel
        //
        JsonAccessKeyElement accessKeyElement = new JsonAccessKeyElement(accessKey.getAccessKey(), accessKey.getSecretAccessKey());
        PlatformMessageResponse platformMessageResponse = RestLetterChannel.acceptRegisterRequest(request.getRemoteAddr());
        JsonRegisterResponse registerResponse = new JsonRegisterResponse();
        JsonRegisterResponseElement registerResponseElement = new JsonRegisterResponseElement();
        registerResponse.setResponse(registerResponseElement);
        registerResponseElement.setAccessKey(accessKeyElement);
        platformMessageResponse.setPayload(registerResponse);

        //
        // Send Message
        //
        /*
         try {
         SOADispatcher3.send(platformMessageResponse);
         } catch (SOAException ex) {
         throw new ServiceException(ex.getMessage());
         } 
         */
    }

}
