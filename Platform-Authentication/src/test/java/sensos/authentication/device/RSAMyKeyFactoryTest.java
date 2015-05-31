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

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.dal.db.DALDBFactory;
import sensos.authentication.dal.db.DAODBAuthentication;

/*
 * This class needs to perform and verify following tasks:
 * 
 * (1) Crypt the Data.
 * (2) Store the crypted data into the database
 * (3) Decrypt the Data.
 */
public class RSAMyKeyFactoryTest {
    
    Logger logger = Logger.getLogger(RSAMyKeyFactoryTest.class.getName());


    public RSAMyKeyFactoryTest() { }

    @BeforeClass
    public static void setUpClass() throws Exception { }

    @AfterClass
    public static void tearDownClass() throws Exception { }
    
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }

    /**
     * AuthenticationTest key encoding , storing it to database, retrieve it there, and decoding it.
     */
    @Test
    public void test() {

        final String ACCESS_KEY ="accesskey123";
        
        DALDBFactory df = new DALDBFactory(DALDBFactory.Mode.Test);
        
        DAODBAuthentication dao = null;

        logger.log(Level.INFO, "Create an instance of keyFactory and assign a DAO for it.");
        RSADeviceKeyFactory keyFactory = new RSADeviceKeyFactory( dao = df.getDAOAuthentication());

        logger.log(Level.INFO, "Generate both private and public key, and give private key back. Private key to database.");
        KeySet ks = keyFactory.generateKeySet(1L);

        PrivateAccessKey privateAccessKey = ks.getPrivateAccessKey();
        BigInteger m = new BigInteger( privateAccessKey.getPrivateKey() );
        BigInteger e = new BigInteger( privateAccessKey.getPrivateExponent() );        
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(m, e);
        RSAPublicKeySpec  publicKeySpec = ks.getPublicAccessKey().getRSAPublicKeySpec();
        
        logger.log(Level.INFO, "Create public and private keys.");
        
        KeyFactory fact=null;
        PublicKey  publicKey=null;
        PrivateKey privateKey = null;
        
        try {
            fact = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RSAMyKeyFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * We do not need public key, because we return accesskey and shared secret for the device.
         */
        try {
            privateKey = fact.generatePrivate(privateKeySpec);
            publicKey = fact.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(RSAMyKeyFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        logger.log(Level.INFO, "Encrypt.");
        byte[] bs = DeviceSecurity.encrypt(privateKey, ACCESS_KEY.getBytes(), DeviceSecurity.Mode.RSA);
        logger.log(Level.INFO, "Encryption is: {0}", new String(bs));
        
        logger.log(Level.INFO, "Decrypt.");
        bs = DeviceSecurity.decrypt(publicKey, bs, DeviceSecurity.Mode.RSA);
        logger.log(Level.INFO, "Result is: {0}", new String(bs));
        
        
        logger.log(Level.INFO, "Check result");
        String _s = new String(bs);
        
        boolean r = ACCESS_KEY.equals(_s);
        logger.log(Level.INFO, "Match is: {0}", r);
        
        
        if(r)
            logger.log(Level.INFO, "This test was valid.");
        
        assertEquals(r, true);
        
    }

}