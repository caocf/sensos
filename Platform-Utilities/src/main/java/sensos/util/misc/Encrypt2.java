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
package sensos.util.misc;

import sun.misc.BASE64Encoder;



import sun.misc.BASE64Decoder;
 
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypt example for AES.
 * @author sensos
 */
public class Encrypt2 {
 
    final static int KEY_LENGTH=126;
    
  public static SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
	byte[] bytes = new byte[16];
	File f = new File("sample_aes_key");
	SecretKey key = null;
	SecretKeySpec spec = null;
	if (f.exists()) {
	  new FileInputStream(f).read(bytes);
	} else {
	   KeyGenerator kgen = KeyGenerator.getInstance("AES");
	   kgen.init(KEY_LENGTH);
	   key = kgen.generateKey();
	   bytes = key.getEncoded();
	   new FileOutputStream(f).write(bytes);
	}
	spec = new SecretKeySpec(bytes,"AES");
	return spec;
  }
  public static void encrypt(String text) throws Exception {
	SecretKeySpec spec = getKeySpec();
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.ENCRYPT_MODE, spec);
	BASE64Encoder enc = new BASE64Encoder();
	System.out.println(enc.encode(cipher.doFinal(text.getBytes())));
  }
  public static void decrypt(String text) throws Exception {
	SecretKeySpec spec = getKeySpec();
	Cipher cipher = Cipher.getInstance("AES");
	cipher.init(Cipher.DECRYPT_MODE, spec);
	BASE64Decoder dec = new BASE64Decoder();
	System.out.println(new String(cipher.doFinal(dec.decodeBuffer(text))));
  }
  public static void main(String[] args) throws Exception {
	
      String mode = "encrypt";
	String text = "zameer";
	if (mode.equals("encrypt")) {
	  encrypt(text);
	} else if (mode.equals("decrypt")) {
	  decrypt(text);
	}
  }
}

