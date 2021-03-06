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

import java.io.Serializable;

import sensos.contracts.messaging.PayloadResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sensos
 */
public class JsonConfigurationResponse implements Serializable, PayloadResponse {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7629188091972247620L;
	JsonConfigurationResponseElement response;
    
    public JsonConfigurationResponse() {
        
        response = new JsonConfigurationResponseElement();
        
    }

    @JsonProperty("Response")
    public JsonConfigurationResponseElement getResponse() {
        return response;
    }

    public void setResponse(JsonConfigurationResponseElement response) {
        this.response = response;
    }
    

    
    
}
