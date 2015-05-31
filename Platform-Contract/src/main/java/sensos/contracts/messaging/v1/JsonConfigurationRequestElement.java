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
public class JsonConfigurationRequestElement implements Serializable {

	private static final long serialVersionUID = 7082929966241894085L;
	
	private String method = "configure";
    private List<String> configs = new ArrayList<String>();

    public JsonConfigurationRequestElement() { }

    public String getMethod() {
        return method;
    }

    public List<String> getConfigs() {
        return configs;
    }

    @JsonProperty("Config")
    public void setConfigs(List<String> config) {
        this.configs = config;
    }


}
