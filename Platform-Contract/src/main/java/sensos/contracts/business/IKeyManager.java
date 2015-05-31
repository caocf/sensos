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
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;
import sensos.authentication.bo.*;
/**
 * Interface that contains all key management functions for the device.
 * 
 * Key management is based on 256 bit AES (Advanced Encryption Standard) symmetric encryption.
 * 
 * @see PublicAccessKey
 * @see PrivateAccessKey
 * @see KeyPair             Container for PublicAccessKey and PrivateAccessKey.
 * 
 * @author sensos
 */
public interface IKeyManager {
    
    static final long serialVersionUID = 1L;

    List<PublicAccessKey> getPublicAccessKeys(PlatformUser user);
    
    /**
     * Get PrivateAccessKey by id.
     * @param id
     * @return 
     */
    PrivateAccessKey getPrivateAccessKey(Long id);
    
    /**
     * Get PrivateAccessKey by accessKeyId.
     * @param accessKeyId
     * @return 
     */
    PrivateAccessKey getPrivateAccessKey( String accessKeyId );

    /**
     * Create KeySet including Public and Private keys for the device.
     * @param device
     * @return 
     */
    KeySet createKeySet(Device device);
    
    /**
     * Get KeySet (including Public and Private key) for the device.
     * @param device
     * @return 
     */
    KeySet getKeySet( Device device );
    
    
    /**
     * Validates accessKey.
     * @param accessKey
     * @return is PublicAccessKey valid or not.
     */
    boolean authenticate( PublicAccessKey accessKey );
    
    /**
     * Get PublicAccessKeys for user id
     * @param user
     * @return 
     */
    List<PublicAccessKey> getPublicAccessKeys( Long userId );
    
    /**
     * Get PublicAccessKey by id.
     * @param id
     * @return 
     */
    PublicAccessKey getPublicAccessKey(Long id);
    
    /**
     * Get PublicAccessKey by accessKeyId.
     * @param accessKeyId
     * @return 
     */
    PublicAccessKey getPublicAccessKey( String accessKeyId );
}
