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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import java.io.Serializable;
import sensos.contracts.messaging.PayloadResponse;

/**
 *
 * @author sensos
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class JsonPingResponse implements Serializable, PayloadResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5155621055489621749L;

	JsonPingResponseElement response;

    public JsonPingResponse() {
        
        response = new JsonPingResponseElement();
        
    }
    
    public JsonPingResponseElement getResponse() {
        return response;
    }
    
    @JsonProperty("Request")
    public void setResponse(JsonPingResponseElement response) {
        this.response = response;
    }
    
}
