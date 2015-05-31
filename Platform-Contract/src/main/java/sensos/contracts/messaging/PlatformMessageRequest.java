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
public class PlatformMessageRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1036149133954687498L;

	String id = UUID.randomUUID().toString();

    /**
     * Indicates when the message was sent from device.
     */
    private Date deviceCreated;
    /**
     * DeviceId of the device that created the message.
     */
    private Long deviceId;
    /**
     * Primary owner of the device.
     */
    private Long deviceOwner;
    /**
     * Indicates when platform received the message.
     */
    private Date platformReceived;
    /**
     * Tell whether Platform should send ACK response or not.
     */
    private boolean ackRequired;
    /**
     * Payload
     */
    private PayloadRequest payload;

    public PlatformMessageRequest() {
    }
    
    public PlatformMessageRequest(PayloadRequest payload) {
        this.payload=payload;
    }

    public String getId() {
        
        return id;
        
    }
    
    public PayloadRequest getPayload() {
        return payload;
    }

    public void setPayload(PayloadRequest payload) {
        this.payload = payload;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getDeviceCreated() {
        return deviceCreated;
    }

    public void setDeviceCreated(Date deviceTimestamp) {
        this.deviceCreated = deviceTimestamp;
    }

    public Date getPlatformReceived() {
        return platformReceived;
    }

    public void setPlatformReceived(Date platformReceived) {
        this.platformReceived = platformReceived;
    }

    public boolean isAckRequired() {
        return ackRequired;
    }

    public void setAckRequired(boolean ackRequired) {
        this.ackRequired = ackRequired;
    }

    public Long getDeviceOwnerId() {
        return deviceOwner;
    }
    
    public void setDeviceOwnerId(Long deviceOwner) {
        this.deviceOwner=deviceOwner;
    }

}
