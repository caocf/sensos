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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sensos
 */
public class JsonMessageRequestElement implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1510854931674044412L;
	String method;
    String messageId;
    String timestamp;
    String requireAck;
    List<JsonDataSetElement> dataSets = new ArrayList<JsonDataSetElement>();

    @JsonCreator
    public JsonMessageRequestElement() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimpestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<JsonDataSetElement> getDataSets() {
        return dataSets;
    }

    @JsonProperty("DataSet")
    public void setDataSets(ArrayList<JsonDataSetElement> dataset) {
        //this.dataSets.add(dataset);
        this.dataSets = dataset;
    }

}
