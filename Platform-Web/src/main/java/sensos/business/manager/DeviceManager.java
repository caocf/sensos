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

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import sensos.authentication.bo.*;

import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IDeviceManager;
import sensos.contracts.business.IKeyManager;
import sensos.contracts.ejb.EjbConstants;
import sensos.dal.db.DAODBDevice;

/**
 * Implementation to manage device components. 
 * 
 * @see sensos.contracts.business.IDeviceManager
 * @author sensos
 */
@Singleton
@Startup
@Remote(IDeviceManager.class)
@EJB(name = EjbConstants.DEVICE_MANAGER, beanInterface = IDeviceManager.class)
@DependsOn({"Manager"})
public class DeviceManager implements IDeviceManager {

    @EJB
    private IKeyManager keyManager;
    
    private Logger l = Logger.getLogger(this.getClass().getName());
    
    private static DAODBDevice daoDevice;


    /**
     * Initializes DAO-Object.
     */
    @PostConstruct
    public void init() {

        daoDevice = Manager.factoryPlatform.getDAODevice();

    }

    /**
     * 
     * @param accessKey
     * @return 
     */
    @Override
    public Device getDevice( PublicAccessKey accessKey ) {
        
        if(accessKey==null) {
            l.log(Level.SEVERE, "Incoming PublicAccessKey is null.");
            return null;
        }
        
        boolean isAuthenticated = keyManager.authenticate(accessKey);

        if(!isAuthenticated) {
            l.log(Level.WARNING, "PublicAccessKey was not authenticated properly: {0}", accessKey.toString());
            return null;
        }
        else {
            return daoDevice.getDevice( accessKey );
        }

    }
    
    /**
     * 
     * @param s
     * @return 
     */
    @SuppressWarnings("unused")
	private boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    /**
     * Store the device into device catalog and return the 
     * public access key back to the original requestor. 
     * 
     * @param user
     * @return 
     */
    @Override
    public PublicAccessKey createDevice(PlatformUser user) {
    
        //create a new representation of the device
        Device device = new Device();
        device.setCreationDate(new Date());
        device.setVisible(true);
        device.setOwner(user.getId());
        
        //Store the device into database...
        daoDevice.store( device );
        
        KeySet k = keyManager.createKeySet( device );
        
        return k.getPublicAccessKey();
        
    }


    /**
     * 
     * @param user
     * @return 
     */
    public List<PublicAccessKey> getPublicAccessKeys( PlatformUser user ) {
        
        return daoDevice.getAccessKeys( user );
        
    }

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public Device getDevice(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param user
     * @return 
     */
    @Override
    public List<Device> getDevices(PlatformUser user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
