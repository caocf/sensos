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
package sensos.platform.rest.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import sensos.contracts.messaging.v1.JsonErrorResponse;
import sensos.contracts.messaging.v1.JsonErrorResponseElement;

/**
 * TODO: Appropriate exceptionMapper handling.
 *
 * @author sensos
 */
@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    private Logger l = Logger.getLogger(this.getClass().getName());
    private static final Response RESPONSE;
    private static final JsonErrorResponse JSON = new JsonErrorResponse();

    static {
        RESPONSE = Response.status(500).entity(JSON).build();
    }

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Throwable ex) {

        JsonErrorResponseElement e = new JsonErrorResponseElement();

        //
        // Usually we do not want to pass detailed information out.
        // TODO: For product environment the error handling should be different.
        e.setCode("500");
        e.setMessage(ex.getMessage());

        JSON.setResponse(e);

        l.log(Level.INFO, "Error occured: {0}", ex.getMessage());

        return RESPONSE;

    }

}
