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

/**
 *
 * @author sensos
 */
public class JsonRegisterResponseElement implements Serializable {

    String method;
    String status;
    String statusCode;
    String statusMessage;
    JsonAccessKeyElement accessKey;

    public JsonRegisterResponseElement() {
    }

    public JsonAccessKeyElement getAccessKey() {
        return accessKey;
    }

    @JsonProperty("AccessKey")
    public void setAccessKey(JsonAccessKeyElement accessKey) {
        this.accessKey = accessKey;
    }

    public String getMethod() {
        return method;
    }

    @JsonProperty("Method")
    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("StatusCode")
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @JsonProperty("StatusMessage")
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}