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
package sensos.contracts.business;

import java.util.List;
import javax.ejb.Remote;
import sensos.authentication.bo.PublicAccessKey;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;

/**
 * Contract to manage devices.
 * @author sensos
 */
@Remote
public interface IDeviceManager {
    
    /**
     * Create a new instance of a device for a defined user. 
     * As a part of succesful creation, return AccessKey to PlatformUser.
     * 
     * @param user
     * @return 
     */
    public PublicAccessKey createDevice(PlatformUser user);
    
    /**
     * Get Device using PublicAccessKey.
     * @param accessKey
     * @return if PublicAccessKey is valid returns also Device and PlatformUser information.
     */
    public Device getDevice( PublicAccessKey accessKey );
    
    /**
     * Get a new instance of the device by device id.
     * @param id
     * @return 
     */
    public Device getDevice(Long id);
    

    
    /**
     * 
     * @param user
     * @return 
     */
    public List<Device> getDevices(PlatformUser user);
        
}
