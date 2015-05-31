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

import javax.ejb.Remote;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;

/**
 * Contract to Manage users.
 *
 * @author sensos
 */
@Remote
public interface IUserManager {

    static final long serialVersionUID = 1L;

    /**
     * Get new instance of user by userId.
     *
     * @param u
     * @return
     */
    public PlatformUser getUser(Long userId);

    /**
     * Get new instance of user by username.
     *
     * @param u
     * @return
     */
    public PlatformUser getUserByUsername(String username);

    /**
     * Get new instance of user by email.
     *
     * @param email
     * @return
     */
    public PlatformUser getUserByEmail(String email);

    /**
     * Store user.
     *
     * @param user
     * @param hashPassword executes a password hashing algorithm if true. Can be
     * set for false, if for example, user object-contains already hashed
     * password and it needs to be updated.
     * @return
     */
    public PlatformUser storeUser(PlatformUser user);

    /**
     * Update user information.
     *
     * @param user
     * @return
     */
    public PlatformUser updateUser(PlatformUser user);

    /**
     * Update password for user.
     *
     * First seeks the user by username. If not found with username, seeks with
     * email address.
     *
     * @param _username
     * @param _password
     */
    public boolean updatePassword(String _username, String _password);

    /**
     * Remove a user from system.
     *
     * @param user
     */
    public void removeUser(PlatformUser user);

    /**
     * Authenticate user by username. 
     * 
     * Authentication first executed by username.
     * If authentication by username/password is unsuccessful, the
     * authentication is then done by email/password.
     * 
     * @param username
     * @param password
     * @return
     */
    public boolean authenticateUser(String username, String password);

    /**
     * Get device owner.
     * @param device 
     */
    public PlatformUser getDeviceOwner(Device device);

}
