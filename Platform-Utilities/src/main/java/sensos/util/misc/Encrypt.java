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

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;

/**
 * Encrypt example for AES.
 *
 * @author sensos
 */
class Encrypt {

    final static int KEY_LENGTH = 126;

    public static void main(String[] args) throws Exception {

        String message = "Message to Decode";

        KeyGenerator key = KeyGenerator.getInstance("AES");
        key.init(KEY_LENGTH);

        SecretKey s = key.generateKey();
        byte[] raw = s.getEncoded();

        SecretKeySpec sskey = new SecretKeySpec(raw, "AES");

        Cipher c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, s);

        byte[] encrypted
                = c.doFinal(message.getBytes());
        System.out.println("encrypted string: " + (encrypted).toString());

    }
    
}
