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
import sensos.contracts.messaging.PayloadResponse;

/**
 *
 * @author sensos
 */
public class JsonUnregisterResponse implements Serializable, PayloadResponse {

    private static final float version = 1.0f;

    JsonUnregisterResponseElement response;

    public JsonUnregisterResponse() {

        response = new JsonUnregisterResponseElement();

    }

    @JsonProperty("Response")
    public void setResponse(JsonUnregisterResponseElement response) {
        this.response = response;
    }

    @JsonProperty("Response")
    public JsonUnregisterResponseElement getResponse() {
        return response;
    }
    
    public String toString() {
        
        if(response != null)
            return "JsonUnregisterResponse[" + response.toString() + "]";
        else
            return "JsonUnregisterResponse[ ]";
        
    }

}
