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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This class executes Encryption/Decryption mechanisms for the Device.
 *
 * @author sensos
 */
public class DeviceSecurity {

	/**
	 * Available modes for Encryption / Decryption.
	 */
	public enum Mode {

		RSA("RSA"), AES("AES");
		String s;

		Mode(String s) {
			this.s = s;
		}

		public String toString() {
			return s;
		}
	};

	/**
	 * Encrypt the incoming data.
	 *
	 * @param privateKey
	 * @param data
	 * @return
	 */
	public static byte[] encrypt(PrivateKey privateKey, byte[] data, Mode mode) {

		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance(mode.toString());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (NoSuchPaddingException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (InvalidKeyException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		byte[] cipherData = null;

		try {
			cipherData = cipher.doFinal(data);
		} catch (IllegalBlockSizeException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (BadPaddingException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return cipherData;

	}

	/**
	 * Decrypt the incoming Data.
	 *
	 * @param publicKey
	 * @param data
	 * @return
	 */
	public static byte[] decrypt(PublicKey key, byte[] data, Mode mode) {

		Cipher cipher = null;

		try {
			cipher = Cipher.getInstance(mode.toString());
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		byte[] cipherData = null;

		try {
			cipherData = cipher.doFinal(data);
		} catch (IllegalBlockSizeException | BadPaddingException ex) {
			Logger.getLogger(DeviceSecurity.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return cipherData;

	}
}
