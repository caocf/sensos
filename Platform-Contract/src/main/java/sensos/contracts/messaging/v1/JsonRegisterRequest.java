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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import java.io.Serializable;
import sensos.contracts.messaging.PayloadRequest;

/**
 *
 * @author sensos
 */
@JsonSerialize(include = Inclusion.NON_EMPTY)
public class JsonRegisterRequest implements Serializable, PayloadRequest {

    public JsonRegisterRequest() {
    }

    private static final float version = 1.0f;
    JsonRegisterRequestElement request;
    String remoteAddr;

    @JsonProperty("Request")
    public JsonRegisterRequestElement getRequest() {
        return request;
    }

    public void setRequest(JsonRegisterRequestElement request) {
        this.request = request;
    }

    @JsonIgnore
    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    @Override
    public JsonAccessKeyElement getAccessKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
