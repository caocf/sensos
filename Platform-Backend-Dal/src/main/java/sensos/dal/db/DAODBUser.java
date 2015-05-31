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
package sensos.dal.db;

import sensos.dal.DAODB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;

/**
 * DAO to manage users.
 *
 * @author sensos
 */
public class DAODBUser extends DAODB {

    private Logger l = Logger.getLogger(this.getClass().getName());

    public DAODBUser() {
    }

    public DAODBUser(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }

    /**
     * Store user into the database.
     *
     * @param u
     * @return
     */
    public PlatformUser store(PlatformUser u) {

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(u);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();

        return getUserByUsername(u.getUsername());

    }

    /**
     * Update user into Database. Before storing, the user is added into Cache.
     *
     * @param u
     */
    public PlatformUser update(PlatformUser u) {

        em.getTransaction().begin();
        u = em.merge(u);
        em.getTransaction().commit();

        return u;

    }

    /**
     * Retrieve PlatformUser from database.
     *
     * @param u
     * @return
     */
    public PlatformUser retrieve(PlatformUser u) {

        return em.find(PlatformUser.class, u.getId());

    }

    /**
     * Retrieve PlatformUser from database using userId.
     *
     * @param userId
     * @return
     */
    public PlatformUser retrieve(Long userId) {

        return retrieve(new PlatformUser(userId));

    }

    /**
     * Get user by email
     *
     * @param email
     * @return
     */
    public PlatformUser getUserByEmail(String email) {

        if (email == null) {
            throw new IllegalStateException("Incoming email cannot be null.");
        }

        PlatformUser u = null;

        try {
            u = em.createQuery("Select u FROM PlatformUser u WHERE u.email = :email", PlatformUser.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (Exception e) {

            l.log(Level.WARNING, "No user provided for email: {0}", email);

        }

        return u;

    }

    /**
     * Get user by username
     *
     * @param username
     * @return
     */
    public PlatformUser getUserByUsername(String username) {

        if (username == null) {
            throw new IllegalStateException("Incoming username cannot be null.");
        }

        PlatformUser u = null;

        try {

            u = em.createQuery("Select u FROM PlatformUser u WHERE u.username = :username", PlatformUser.class)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception e) {

            l.log(Level.WARNING, "No user provided for username: {0}", username);

        }

        return u;

    }

    /**
     * Get user by device.
     * @param device
     * @return 
     */
    public PlatformUser getDeviceOwner(Device device) {

        if (device == null) {
            throw new IllegalStateException("Incoming device cannot be null.");
        }

        PlatformUser u = null;
        Long userId = null;

        //
        //First get the device
        //
        try {

            //Then get the device
            device = em.createQuery("SELECT d from Device d WHERE d.id = :deviceId", Device.class)
                    .setParameter("deviceId", device.getId())
                    .getSingleResult();

        } catch (Exception e) {

            l.log(Level.WARNING, "No device provided for deviceId: {0}", userId);

        }
        
        if(device==null) {
            throw new IllegalStateException("Handled device cannot be null.");
        }
        
        userId = device.getOwner();

        //
        //Then get the owner
        //
        
        try {

            u = em.createQuery("Select u FROM PlatformUser u WHERE u.id = :id", PlatformUser.class)
                    .setParameter("id", userId)
                    .getSingleResult();

        } catch (Exception e) {

            l.log(Level.WARNING, "No user provided for id: {0}", userId);

        }

        return u;

    }

    /**
     * Get all user id's from database.
     *
     * @return
     */
    public List<Long> getUserIds() {

        return em.createNamedQuery("User.findAll").getResultList();

    }

    /**
     * Authenticate user according to Username and Password. This method is also
     * used in BASIC-authentication.
     *
     * First try to authenticate with username and password.. if authentication
     * does not succeed, switch to email and password.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean authenticate(String username, String password) {

        //prechecks...
        if (username == null || "".equals(username)) {
            return false;
        }
        if (password == null || "".equals(password)) {
            return false;
        }

        //
        PlatformUser user = new PlatformUser();
        user.setUsername(username);
        user = getUserByUsername(username);
        if (user == null) {
            user = getUserByEmail(username);
        }
        if (user == null) {
            return false;
        }

        if (password.equals(user.getPasswordHash())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @param user
     */
    public void removeUser(PlatformUser user) {

        em.getTransaction().begin();
        PlatformUser _user = em.merge(user);
        em.remove(_user);
        em.getTransaction().commit();

    }

}
