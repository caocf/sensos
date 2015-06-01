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
public class JsonErrorResponse implements Serializable, PayloadResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9154707177744880701L;

	JsonErrorResponseElement response;

    public JsonErrorResponse() {

        response = new JsonErrorResponseElement();

    }

    public JsonErrorResponseElement getResponse() {
        return response;
    }

    @JsonProperty("Response")
    public void setResponse(JsonErrorResponseElement response) {
        this.response = response;
    }

    public String toString() {

        if (response != null) {
            return "JsonErrorResponse[" + response.toString() + "]";
        } else {
            return "JsonErrorResponse[ ]";
        }

    }

}
