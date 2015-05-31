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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import sensos.authentication.bo.KeySet;
import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.dal.db.DALDBFactory;
import sensos.authentication.dal.db.DAODBAuthentication;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author sensos
 */
public class RSADeviceAuthenticatorTest {

    Logger logger = Logger.getLogger(RSADeviceAuthenticatorTest.class.getName());
    BASE64Encoder encode = new BASE64Encoder();
    BASE64Decoder decode = new BASE64Decoder();
    
    public RSADeviceAuthenticatorTest() { }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    DALDBFactory df;

    public DALDBFactory getDf() {
        return df;
    }

    public void setDf(DALDBFactory df) {
        this.df = df;
    }
    
    
    
    /**
     * AuthenticationTest of authenticate method, of class RSADeviceAuthenticator.
     */
    @Test
    public void testAuthenticate() throws Exception {
        
        /**
         * First we generate a DAL-component that stores and retrieves AccessKeys from database.
         */
        logger.log(Level.INFO, "Create an instance of keyFactory and assign a DAO for it.");        
        if(df==null)
            df = new DALDBFactory(DALDBFactory.Mode.Test);
        DAODBAuthentication dao = df.getDAOAuthentication();
        
        RSADeviceKeyFactory keyFactory = new RSADeviceKeyFactory( dao = df.getDAOAuthentication());
        
        /**
         * Then we generate public and private keys.
         * We generate these keys for a device with id '1'.
         */
        Long deviceId = 1L;
        logger.log(Level.INFO, "Generate both private and public key for a device. Store private key to a database.");
        KeySet keySet = keyFactory.generateKeySet(deviceId);
        PrivateAccessKey privateAccessKey = keySet.getPrivateAccessKey();
        PublicAccessKey  publicAccessKey =  keySet.getPublicAccessKey();
        
        //Now use freshly created key
        RSADeviceAuthenticator authenticator = new RSADeviceAuthenticator(dao);
        
        //Now use the publicAccess key to authenticate the device again.
        boolean ok = authenticator.authenticate(publicAccessKey);
        
        assertEquals(ok,true);
                
    }
    
    public byte[] _crypted; 
    public String _accessKey;
    
}
