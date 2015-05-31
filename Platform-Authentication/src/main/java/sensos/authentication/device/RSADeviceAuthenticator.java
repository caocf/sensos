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
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sun.misc.BASE64Decoder;

/**
 * Asymmetric DeviceAuthenticator.
 *
 * @author Valtter Rajakannas
 *
 * @see AESDeviceAuthenticator
 */
// @Local
// @Stateless
public class RSADeviceAuthenticator extends IDeviceAuthenticator {

	BASE64Decoder decode = new BASE64Decoder();

	private Logger logger = Logger.getLogger(RSADeviceAuthenticator.class
			.getName());

	private DAODBAuthentication DAODBAccessKey;

	public RSADeviceAuthenticator() {
	}

	public RSADeviceAuthenticator(DAODBAuthentication DAODBAccessKey) {
		this.DAODBAccessKey = DAODBAccessKey;
	}

	/**
	 * Authenticate incoming PublicAccessKey.
	 *
	 * @param accessKey
	 * @return was authentication successful or not.
	 * @throws DeviceAuthenticatorException
	 */
	public boolean authenticate(PublicAccessKey publicAccessKey)
			throws DeviceAuthenticatorException {

		if (publicAccessKey.getAccessKey() == null) {
			throw new DeviceAuthenticatorException("No accessKey defined");
		}

		if (publicAccessKey.getSecretAccessKey() == null) {
			throw new DeviceAuthenticatorException("No secretAccessKey defined");
		}

		boolean isAuthenticated = false;

		/* ---------------- */
		// Retrieve the privateAccessKey.
		PrivateAccessKey pk = new PrivateAccessKey();
		pk.setAccessKey(publicAccessKey.getAccessKey());

		try {

			pk = DAODBAccessKey.retrieveByKey(pk);

		} catch (NoResultException e) {

			throw new DeviceAuthenticatorException("AccessKey not found");

		}

		// get the private key.
		BigInteger _pk = new BigInteger(pk.getPrivateKey());
		BigInteger _pe = new BigInteger(pk.getPrivateExponent());
		RSAPrivateKeySpec pks = new RSAPrivateKeySpec(_pk, _pe);

		KeyFactory kf = null;

		try {
			kf = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(RSADeviceAuthenticator.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		PrivateKey pri = null;
		PublicKey pub = null;

		try {
			pri = kf.generatePrivate(pks);
			pub = kf.generatePublic(publicAccessKey.getRSAPublicKeySpec());
		} catch (InvalidKeySpecException ex) {
			throw new DeviceAuthenticatorException(ex);
		}

		String sSecretAccessKey = publicAccessKey.getSecretAccessKey();
		byte[] baSecretAccessKey = null;
		try {
			baSecretAccessKey = decode.decodeBuffer(sSecretAccessKey);
		} catch (IOException ex) {
			Logger.getLogger(RSADeviceAuthenticator.class.getName()).log(
					Level.SEVERE, null, ex);
			throw new DeviceAuthenticatorException(
					"Error in executing authentication");
		}

		if (sSecretAccessKey == null) {
			throw new DeviceAuthenticatorException(
					"Error in executing authentication");
		}

		byte[] res = DeviceSecurity.decrypt(pub, baSecretAccessKey,
				DeviceSecurity.Mode.RSA);

		if (res == null) {
			throw new DeviceAuthenticatorException(
					"Error in executing authentication");
		}

		String accessKeyAnswer = new String(res);

		isAuthenticated = accessKeyAnswer.equals(accessKeyAnswer);

		if (logger.isLoggable(Level.INFO)) {

			logger.log(Level.INFO, "Checking authentication...");

			if (isAuthenticated) {

				logger.log(Level.INFO, "Keys matches");

			} else {

				logger.log(Level.INFO, "Keys do not match.");

			}

			logger.log(Level.INFO, "Check complete.");

		}

		// Return response
		return isAuthenticated;

	}

}
