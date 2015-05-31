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

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;

import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sensos.authentication.device.AESDeviceAuthenticator;
import sensos.authentication.device.AESDeviceKeyFactory;
import sensos.authentication.device.DeviceAuthenticatorException;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IKeyManager;

/**
 * Implementation to manage key components.
 *
 * @see sensos.contracts.business.IKeyManagerPrivate
 * @author sensos
 */
@Singleton
@Startup
@Remote(IKeyManager.class)
@DependsOn("Manager")
public class KeyManager implements IKeyManager, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7057944471508485342L;
    private static DAODBAccessKey daoAccessKey;
    private static DAODBAuthentication daoAuthentication;
    private static AESDeviceAuthenticator deviceAuthenticator;
    private static AESDeviceKeyFactory keyFactory;

    /**
     * Initializes DAO-Object.
     */
    @PostConstruct
    public void init() {

        daoAccessKey = Manager.factoryPlatform.getDAOAccessKey();
        daoAuthentication = Manager.factoryPlatform.getDAOAuthentication();
        deviceAuthenticator = new AESDeviceAuthenticator(daoAuthentication);
        keyFactory = new AESDeviceKeyFactory(daoAuthentication);

    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public List<PublicAccessKey> getPublicAccessKeys(PlatformUser user) {

        return daoAccessKey.getPublicAccessKeys(user.getId());

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PublicAccessKey getPublicAccessKey(Long id) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     *
     * @param accessKeyId
     * @param securedAccessKey
     * @return
     */
    @Override
    public boolean authenticate(PublicAccessKey accessKey) {

        if (accessKey == null) {
            return false;
        }

        //this method could be also executed against cache...
        boolean authenticate = false;
        try {
            authenticate = deviceAuthenticator.authenticate(accessKey);
        } catch (DeviceAuthenticatorException ex) {
            Logger.getLogger(DeviceManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return authenticate;

    }

    /**
     *
     * @param accessKeyId
     * @return
     */
    @Override
    public PublicAccessKey getPublicAccessKey(String accessKeyId) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PrivateAccessKey getPrivateAccessKey(Long id) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     *
     * @param accessKeyId
     * @return
     */
    @Override
    public PrivateAccessKey getPrivateAccessKey(String accessKeyId) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    /**
     *
     * @param device
     * @return
     */
    @Override
    public KeySet createKeySet(Device device) {

        return keyFactory.generateKeySet(device.getId());

    }

    /**
     *
     * @param device
     * @return
     */
    @Override
    public KeySet getKeySet(Device device) {

        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public List<PublicAccessKey> getPublicAccessKeys(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
