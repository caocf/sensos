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
package sensos.contracts.messaging.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import sensos.contracts.messaging.PayloadRequest;

/**
 *
 * @author sensos
 */
public class JsonPingRequest implements Serializable, PayloadRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2482639297975090144L;
	
	JsonPingRequestElement request;

    @JsonProperty("Request")
    public JsonPingRequestElement getRequest() {
        return request;
    }

    public void setRequest(JsonPingRequestElement request) {
        this.request = request;
    }

    @Override
    public JsonAccessKeyElement getAccessKey() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
