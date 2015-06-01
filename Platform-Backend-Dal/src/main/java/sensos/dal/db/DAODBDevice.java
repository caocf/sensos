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

import java.util.List;

import javax.persistence.EntityManagerFactory;

import sensos.authentication.bo.PublicAccessKey;
import sensos.bo.device.Device;
import sensos.bo.user.PlatformUser;
import sensos.dal.DAODB;

/**
 * DAO to manage devices.
 *
 * @author sensos
 */
public class DAODBDevice extends DAODB {

    public DAODBDevice() { }

    public DAODBDevice(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }

    /**
     * Get the Device with PublicAccessKey
     *
     * @param accessKey
     * @return
     */
    public Device getDevice(PublicAccessKey accessKey) {

        if (accessKey == null) {
            throw new IllegalStateException("Incoming PublicAccessKey cannot be null");
        }

        Device device = null;

        try {

            //Complete the accessKey first
            accessKey = em.createQuery("SELECT ak FROM PublicAccessKey ak WHERE ak.accessKey = :accessKey", PublicAccessKey.class)
                    .setParameter("accessKey", accessKey.getAccessKey())
                    .getSingleResult();

            if (accessKey == null) {
                throw new SecurityException("AccessKey cannot be null.");
            }

            //Then get the device
            device = em.createQuery("SELECT d from Device d WHERE d.id = :deviceId", Device.class)
                    .setParameter("deviceId", accessKey.getDeviceId())
                    .getSingleResult();

            if (device == null) {
                throw new SecurityException("Could not find Device with accesskey.");
            }

        } catch (Exception e) {

        	e.printStackTrace();
        	
        }

        return device;

    }

    /*
     * Stores the device.
     * @param d
     * @return
     
    public Device store(Device d) {

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(d);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();

        return d;

    }*/

    public void store(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<PublicAccessKey> getAccessKeys(PlatformUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
