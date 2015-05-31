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

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sun.misc.BASE64Encoder;

/**
 * Creates public and private keys.
 * 
 * The only problem is that it will generate asymmetric encryption mechanism.
 * 
 * @see RDADeviceAuthenticator
 * @see AESDeviceKeyFactory
 * 
 * @link http://www.javamex.com/tutorials/cryptography/rsa_encryption.shtml
 * 
 * @author sensos
 */
public class RSADeviceKeyFactory extends IDeviceKeyFactory {

	private KeyPairGenerator kpg;

	private BASE64Encoder encode = new BASE64Encoder();

	public RSADeviceKeyFactory(DAODBAuthentication _dao) {

		super(_dao);

		if (_dao == null)
			throw new IllegalStateException(
					"Incoming daoFactory cannot be null");

		try {
			kpg = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(RSADeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
			throw new IllegalStateException(ex);
		}

		kpg.initialize(2048);

	}

	/**
	 * Generates a public and a private key for the given baseStationId.
	 *
	 * Public key is then stored into a database and a private key is returned
	 * for user.
	 * 
	 * @return Private key for the user.
	 *
	 */
	public KeySet generateKeySet(long baseStationId) {

		/*
		 * First we generate KeyPairs.
		 */
		KeySet keySet = new KeySet();
		KeyPair kp = kpg.genKeyPair();
		final PublicKey publicKey = kp.getPublic();
		final PrivateKey privateKey = kp.getPrivate();

		/*
		 * We use RSA-Cipher.
		 */
		KeyFactory fact;

		try {
			fact = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(RSADeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
			throw new IllegalStateException(ex);
		}

		RSAPublicKeySpec pub = null;
		RSAPrivateKeySpec priv = null;

		try {
			pub = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
			priv = fact.getKeySpec(privateKey, RSAPrivateKeySpec.class);
		} catch (InvalidKeySpecException ex) {
			Logger.getLogger(RSADeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
			throw new IllegalStateException(ex);
		}

		/*
		 * We store relevant keys to the database.
		 */
		PrivateAccessKey privateAccessKey = storePrivateAccessKey(
				baseStationId, priv, DeviceSecurity.Mode.RSA);
		keySet.setPrivateAccessKey(privateAccessKey);

		/*
		 * We crypt a shared secret for a publicKey.
		 */
		byte[] crypted = DeviceSecurity.encrypt(privateKey, privateAccessKey
				.getAccessKey().getBytes(), DeviceSecurity.Mode.RSA);

		/**
		 * We assign the key and secretKey for accesskey that is returned for
		 * user.
		 */
		PublicAccessKey publicAccessKey = new PublicAccessKey();

		/*
		 * Set accessKey and corresponding shared secret in BASE64-format.
		 */
		publicAccessKey.setAccessKey(privateAccessKey.getAccessKey());
		publicAccessKey.setSecretAccessKey(encode.encode(crypted));
		publicAccessKey.setRSAPublicKeySpec(pub);

		keySet.setPublicAccessKey(publicAccessKey);

		return keySet;

	}

}