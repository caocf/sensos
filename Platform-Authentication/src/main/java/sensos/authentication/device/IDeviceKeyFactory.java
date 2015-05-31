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
package sensos.authentication.device;

import java.security.spec.RSAPrivateKeySpec;

import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;

/**
 *
 * @author sensos
 */
public abstract class IDeviceKeyFactory {

	private DAODBAuthentication daodbAccessKey;

	public IDeviceKeyFactory(DAODBAuthentication DAODBAccessKey) {

		this.daodbAccessKey = DAODBAccessKey;

	}

	public abstract KeySet generateKeySet(long baseStationId);

	/**
	 * Store the public key into database.
	 */
	void storePublicAccessKey(Long deviceId, PublicAccessKey publicAccessKey) {

		publicAccessKey.setDeviceId(deviceId);
		daodbAccessKey.store(publicAccessKey);

	}

	/**
	 * Store the private key into database.
	 * 
	 * @return PrivateAccessKey that contains a DeviceId.
	 */
	PrivateAccessKey storePrivateAccessKey(Long deviceId, Object priv,
			DeviceSecurity.Mode mode) {

		PrivateAccessKey privateAccessKey = null;

		if (mode == DeviceSecurity.Mode.AES) {

			privateAccessKey = (PrivateAccessKey) priv;

		} else if (mode == DeviceSecurity.Mode.RSA) {

			RSAPrivateKeySpec _priv = (RSAPrivateKeySpec) priv;

			String s = _priv.getModulus().toString();
			String e = _priv.getPrivateExponent().toString();

			privateAccessKey = new PrivateAccessKey();

			privateAccessKey.setPrivateKey(s);
			privateAccessKey.setPrivateExponent(e);

		}
		privateAccessKey.setDeviceId(deviceId);
		privateAccessKey.setMode(mode.toString());
		daodbAccessKey.store(privateAccessKey);
		return privateAccessKey;

	}

}
