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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensos
 */
public class JsonConfigurationResponseElement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2952389959190278148L;

	public JsonConfigurationResponseElement() {
    }

    String status;
    String statusCode;
    String statusMessage;
    String method;
    List<JsonConfigurationResponseConfigureElement> configure = new ArrayList<>();

    @JsonProperty
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @JsonProperty
    public List<JsonConfigurationResponseConfigureElement> getConfigure() {
        return configure;
    }

    public void setConfigure(List<JsonConfigurationResponseConfigureElement> configure) {
        this.configure = configure;
    }

    public void addConfigure(String key, String value) {

        JsonConfigurationResponseConfigureElement j = new JsonConfigurationResponseConfigureElement();
        j.setKey(key);
        j.setValue(value);

        this.configure.add(j);

    }

}
