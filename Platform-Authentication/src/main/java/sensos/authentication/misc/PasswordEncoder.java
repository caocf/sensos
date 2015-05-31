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
package sensos.authentication.misc;

/**
 * File Name : PasswordEncoder.java 
 * Created on: 1 Jul 2010 
 * Created by: Ashish Shukla 
 * Orange Hut Solution Limited. 
 * http://www.orangehut.com
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES Based password encoder.
 * 
 * @author Ashish Shukla
 * 
 */
public class PasswordEncoder {

	private static String SALT_KEY = "This is a saltkey";

	/**
	 * Logger for this class
	 */
	private static PasswordEncoder instance;
	private static Logger l = Logger.getLogger(PasswordEncoder.class.getName());

	/**
	 * Count for the number of time to hash, more you hash more difficult it
	 * would be for the attacker
	 */
	private final static int ITERATION_COUNT = 3;

	/**
	 * Empty Constructor
	 */
	public PasswordEncoder() {

	}

	/**
	 * @return
	 * @author Ashish Shukla
	 */
	public static synchronized PasswordEncoder getInstance() {

		if (instance == null) {
			l.log(Level.INFO, "Returning new instance of: {0}",
					PasswordEncoder.class.getName());
			PasswordEncoder returnPasswordEncoder = new PasswordEncoder();
			return returnPasswordEncoder;
		} else {
			l.log(Level.INFO, "Returning existing instance of: {0}",
					PasswordEncoder.class.getName());
			return instance;
		}

	}

	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public synchronized String encode(String password)
			throws NoSuchAlgorithmException, IOException {
		return encode(password, SALT_KEY);
	}

	/**
	 * 
	 * @param password
	 * @param saltKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @author Ashish Shukla
	 */
	public synchronized String encode(String password, String saltKey)
			throws NoSuchAlgorithmException, IOException {

		String encodedPassword = null;
		byte[] salt = base64ToByte(saltKey);

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);

		byte[] btPass = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < ITERATION_COUNT; i++) {
			digest.reset();
			btPass = digest.digest(btPass);
		}

		encodedPassword = byteToBase64(btPass);

		return encodedPassword;

	}

	/**
	 * @param str
	 * @return byte[]
	 * @throws IOException
	 */
	private byte[] base64ToByte(String str) throws IOException {

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] returnbyteArray = decoder.decodeBuffer(str);
		return returnbyteArray;

	}

	/**
	 * @param bt
	 * @return String
	 * @throws IOException
	 */
	private String byteToBase64(byte[] bt) {

		BASE64Encoder endecoder = new BASE64Encoder();
		String returnString = endecoder.encode(bt);

		return returnString;

	}

}