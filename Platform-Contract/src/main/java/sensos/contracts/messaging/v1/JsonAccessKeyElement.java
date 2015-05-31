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
public class JsonAccessKeyElement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1086856312133669765L;
	
	String key;
    String secret;

    public JsonAccessKeyElement() {
    }

    public JsonAccessKeyElement(String key, String secret) {

        this.key = key;
        this.secret = secret;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String toString() {
        return "JsonAccessKeyElement [ id=" + key + ", key=" + secret + " ]";
    }

}
