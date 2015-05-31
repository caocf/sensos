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
package sensos.business.manager;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IUserManager;
import sensos.dal.db.DAODBUser;

/**
 * Implementation to manage user components.
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote
@DependsOn("Manager")
public class UserManager implements IUserManager, Serializable {

	private static final long serialVersionUID = -5630109513635691116L;
	//private PasswordEncoder passwordEncoder = new PasswordEncoder();
    private static DAODBUser daoUser;

    public UserManager() {
    }

    /**
     * Initializes DAO-Object.
     */
    @PostConstruct
    public void init() {

        daoUser = Manager.factoryPlatform.getDAOUser();

    }

    /**
     * Get user by user id.
     *
     * @param u
     * @return
     */
    @Override
    public PlatformUser getUser(Long u) {

        PlatformUser user = new PlatformUser();
        user.setId(u);

        return daoUser.retrieve(user);

    }

    /**
     * Get user by username
     *
     * @param u
     * @return
     */
    @Override
    public PlatformUser getUserByEmail(String u) {

        return daoUser.getUserByEmail(u);

    }

    @Override
    public PlatformUser getUserByUsername(String u) {

        return daoUser.getUserByUsername(u);

    }

    /**
     * Stores user.
     *
     * @param user
     */
    @Override
    public PlatformUser storeUser(PlatformUser u) {

        if (u == null) {
            throw new IllegalStateException("Incoming user object cannot be null.");
        }

        hashPassword(u);

        u.setCreationDate(new Date());
        u.setStatus(PlatformUser.Status.Approved);
        u.setVisible(true);
        u.setExternal(false);
        u.setUserEnabled(true);

        return daoUser.store(u);

    }

    /**
     *
     * @param u
     */
    private void hashPassword(PlatformUser u) {

        String password = u.getPasswordHash();
        String encodedPassword = "";

        if (password == null || "".equals(password)) {
            throw new IllegalStateException("Incoming password cannot be null or empty.");
        }

        /*
        try {
            encodedPassword = passwordEncoder.encode(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        u.setPasswordHash(encodedPassword);

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public PlatformUser updateUser(PlatformUser user) {
        return daoUser.update(user);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean authenticateUser(String username, String password) {

        boolean result = false;

        if (username == null) {
            throw new IllegalStateException("Incoming username cannot be null");
        }
        if (password == null) {
            throw new IllegalStateException("Incoming password cannot be null");
        }

        try {

            password = null;//passwordEncoder.encode(password);

            PlatformUser user = null;

            user = daoUser.getUserByUsername(username);

            if (user == null) {
                user = daoUser.getUserByEmail(username);
            }

            if (user == null) {
                return false;
            }

            String enc = user.getPasswordHash();

            result = password.equals(enc);

        } catch (Exception ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    /**
     *
     * @param username
     * @param password
     */
    @Override
    public boolean updatePassword(String username, String password) {

        boolean isUpdateSuccesful = false;

        PlatformUser u = daoUser.getUserByUsername(username);

        if (u == null) {
            u = daoUser.getUserByEmail(username);
        }
        if (u == null) {
            return false;
        }

        u.setLastProfileUpdate(new Date());
        u.setPasswordHash(password);
        hashPassword(u);

        try {

            daoUser.update(u);
            isUpdateSuccesful = true;
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
            isUpdateSuccesful = false;
        }

        return isUpdateSuccesful;

    }

    @Override
    public void removeUser(PlatformUser user) {

        if (user == null) {
            throw new IllegalArgumentException("Incoming user cannot be null.");
        }

        daoUser.removeUser(user);

    }

    @PreDestroy
    public void destroy() {

        System.out.println("Closing UserManager...");

    }

    @Override
    public PlatformUser getDeviceOwner(Device device) {

        if (device == null) {
            throw new IllegalArgumentException("Incoming user cannot be null.");
        }

        return daoUser.getDeviceOwner(device);

    }

}
