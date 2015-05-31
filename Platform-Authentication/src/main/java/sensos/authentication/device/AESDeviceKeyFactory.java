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

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sun.misc.BASE64Encoder;

/**
 * Symmetric KeyFactory for authentication.
 *
 * @author sensos
 */
public class AESDeviceKeyFactory extends IDeviceKeyFactory {

	// An issue in choosing an encryption key size in Java is that by default,
	// current versions of the JDK have a deliberate key size restriction built
	// in.
	// If you try to perform, say, 256-bit AES encryption with the default JDK,
	// you'll find that it dutifully throws an InvalidKeyException, complaining
	// with the not-too-explicit message
	// "Illegal key size or default parameters".
	//
	// If you get this exception, you're probably not doing anything wrong:
	// you've just hit an arbitrary restriction imposed by (at least Sun's)
	// JDK with default settings.
	public static final int KEY_LENGHT = 128;
	public static final String CIPHER_ALGORITHM = "AES";
	public static final String KEY_ALGORITHM = "AES";

	public AESDeviceKeyFactory(DAODBAuthentication _dao) {

		super(_dao);

	}

	// private PasswordEncoder passwordEncoder = new PasswordEncoder();
	BASE64Encoder encode = new BASE64Encoder();

	/**
	 * Generate AES keys for device.
	 *
	 * @param baseStationId
	 * @return
	 */
	@Override
	public KeySet generateKeySet(long baseStationId) {

		KeySet keySet = new KeySet();
		PrivateAccessKey privateAccessKey = new PrivateAccessKey();
		PublicAccessKey publicAccessKey = new PublicAccessKey();
		keySet.setPrivateAccessKey(privateAccessKey);
		keySet.setPublicAccessKey(publicAccessKey);

		String random = UUID.randomUUID().toString().replace("-", "");
		KeyGenerator key = null;
		try {
			key = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		key.init(KEY_LENGHT);

		SecretKey s = key.generateKey();
		byte[] raw = s.getEncoded();

		SecretKeySpec aesKey = new SecretKeySpec(raw, KEY_ALGORITHM);

		Cipher c = null;
		try {
			c = Cipher.getInstance(CIPHER_ALGORITHM);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		try {
			c.init(Cipher.ENCRYPT_MODE, aesKey);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		byte[] encrypted = null;

		try {
			encrypted = c.doFinal(random.getBytes("UTF-8"));
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(AESDeviceKeyFactory.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		publicAccessKey.setAccessKey(privateAccessKey.getAccessKey());
		publicAccessKey.setSecretAccessKey(encode.encode(encrypted));

		privateAccessKey.setPrivateKey(random);
		privateAccessKey.setPrivateExponent(encode.encode(raw));

		privateAccessKey = storePrivateAccessKey(baseStationId,
				privateAccessKey, DeviceSecurity.Mode.AES);

		storePublicAccessKey(baseStationId, publicAccessKey);

		return keySet;

	}

}
