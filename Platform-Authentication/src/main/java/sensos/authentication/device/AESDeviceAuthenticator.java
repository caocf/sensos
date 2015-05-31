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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.NoResultException;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sun.misc.BASE64Decoder;

/**
 * Symmetric DeviceAuthenticator.
 *
 * @author sensos
 */
public class AESDeviceAuthenticator extends IDeviceAuthenticator {

	private Logger logger = Logger.getLogger(RSADeviceAuthenticator.class
			.getName());
	private DAODBAuthentication DAODBAccessKey;
	private static BASE64Decoder decoder = new BASE64Decoder();

	public AESDeviceAuthenticator(DAODBAuthentication DAODBAccessKey) {

		this.DAODBAccessKey = DAODBAccessKey;

	}

	@Override
	public boolean authenticate(PublicAccessKey publicAccessKey)
			throws DeviceAuthenticatorException {

		String accessKey = publicAccessKey.getAccessKey();
		String secretAccessKey = publicAccessKey.getSecretAccessKey();
		PrivateAccessKey pk = new PrivateAccessKey();
		pk.setAccessKey(accessKey);

		try {

			pk = DAODBAccessKey.retrieveByKey(pk);

		} catch (NoResultException e) {

			return false;

		}

		String privateExponent = pk.getPrivateExponent();

		byte[] raw = null;

		try {
			raw = decoder.decodeBuffer(privateExponent);
		} catch (IOException ex) {

			Logger.getLogger(AESDeviceAuthenticator.class.getName()).log(
					Level.SEVERE, null, ex);

		}

		SecretKeySpec aesKey = new SecretKeySpec(raw, "AES");

		Cipher c = null;
		try {
			c = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		try {
			c.init(Cipher.DECRYPT_MODE, aesKey);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		byte[] secretAccess = null;

		try {
			try {
				secretAccess = c.doFinal(decoder.decodeBuffer(secretAccessKey));
			} catch (IOException ex) {
				Logger.getLogger(AESDeviceAuthenticator.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		pk = DAODBAccessKey.retrieveByKey(pk);

		boolean match = (pk.getPrivateKey().equals(new String(secretAccess)));

		return match;

	}

}
