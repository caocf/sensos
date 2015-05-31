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
package sensos.dock.rest.v1;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sensos.authentication.bo.PublicAccessKey;
import sensos.bo.device.Device;
import sensos.contracts.business.IDeviceManager;
import sensos.contracts.ejb.EjbConstants;
import sensos.contracts.messaging.PayloadRequest;
import sensos.contracts.messaging.v1.JsonAccessKeyElement;

/**
 *
 * @author sensos
 */
public class DockAuthenticator {

    //
    // For some reason @EJB injection does not work here.
    //
    @EJB
    IDeviceManager deviceManager;

    public DockAuthenticator() {

        //
        // We first try to seek the resource from EAR, if not found, we search from the WAR itself.
        //
        if (deviceManager == null) {

            try {
                deviceManager = (IDeviceManager) new InitialContext().lookup("java:global/Platform/Platform-Backend-BP/DeviceManager");
            } catch (NamingException ex) {
                //swallow...
            }

        }

        if (deviceManager == null) {

            try {
                deviceManager = (IDeviceManager) new InitialContext().lookup("java:global/Platform-Backend-BP/DeviceManager");
            } catch (NamingException exa) {
                //swallow...java:global/Platform-Backend-BP/DeviceManager
            }

        }

        if (deviceManager == null) {

            try {
                deviceManager = (IDeviceManager) new InitialContext().lookup(EjbConstants.DEVICE_MANAGER);
            } catch (NamingException exa) {
                //swallow...
            }

        }

    }

    protected Device authenticate(PayloadRequest payload) throws ServiceException {

        if (deviceManager == null) {
            throw new SecurityException(IDeviceManager.class.getName() + " has not been initialized properly.");
        }

        if (payload == null) {
            throw new SecurityException("Incoming payload cannot be empty.");
        }

        JsonAccessKeyElement ak = payload.getAccessKey();

        if (ak.getKey() == null || ak.getKey().equals("")) {
            throw new SecurityException("Incoming AccessKey.id cannot be left empty.");
        }

        if (ak.getSecret() == null || ak.getSecret().equals("")) {
            throw new SecurityException("Incoming AccessKey.key cannot be left empty.");
        }

        PublicAccessKey publicAccessKey = new PublicAccessKey();
        publicAccessKey.setAccessKey(ak.getKey());
        publicAccessKey.setSecretAccessKey(ak.getSecret());

        Device device = deviceManager.getDevice(publicAccessKey);

        if (device == null) {
            throw new ServiceException("Incoming accessKey is not valid");
        }

        return device;

    }

}
