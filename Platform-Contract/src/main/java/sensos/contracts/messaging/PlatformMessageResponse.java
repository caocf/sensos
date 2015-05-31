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
package sensos.contracts.messaging;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author sensos
 */
public class PlatformMessageResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5715251151134318882L;

	private final String id = UUID.randomUUID().toString();

    private Date platformCreated;

    private String originalId;

    private PayloadResponse payload;

    public PlatformMessageResponse() {  }
    
    public PlatformMessageResponse(PayloadResponse payload) {
        this.payload=payload;
    }

    public Date getPlatformCreated() {
        return platformCreated;
    }
    
    public String getId() { 
        return id;
    }

    public void setPlatformCreated(Date platformCreated) {
        this.platformCreated = platformCreated;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public PayloadResponse getPayload() {
        return payload;
    }

    public void setPayload(PayloadResponse payload) {
        this.payload = payload;
    }

    
    
}
