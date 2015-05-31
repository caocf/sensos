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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * @link http://stackoverflow.com/questions/5230157/deserialization-of-arrays-with-jackson
 */
public class JsonDataSetElement implements Serializable {

    private String type;
    private String encoding;
    private String data;

    public JsonDataSetElement() {
    }

    /**
     * @link http://www.cowtowncoder.com/blog/archives/2011/07/entry_457.html
     *
     * @param type
     * @param encoding
     * @param data
     */
    @JsonCreator
    public JsonDataSetElement(@JsonProperty("type") String type,
            @JsonProperty("encoding") String encoding,
            @JsonProperty("data") String data) {

        this.type = type;
        this.encoding = encoding;
        this.data = data;

    }

    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

}
