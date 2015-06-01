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

/**
 *
 * @author sensos
 */
public class JsonUnregisterRequestElement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8257565590114812291L;

	public JsonUnregisterRequestElement() {
    }

    String method = "unregister";
    String requireAck;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequireAck() {
        return requireAck;
    }

    public void setRequireAck(String requireAck) {
        this.requireAck = requireAck;
    }

    public String toString() {

        return "JsonUnregisterRequestElement [ method=" + method + ", requireAck=" + requireAck + " ]";

    }

}
