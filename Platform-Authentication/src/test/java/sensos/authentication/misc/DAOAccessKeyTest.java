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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.dal.db.DALDBFactory;
import sensos.authentication.dal.db.DAODBAccessKey;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author sensos
 */
public class DAOAccessKeyTest {
    
    Logger logger = Logger.getLogger(DAODBAccessKey.class.getName());
    
    DALDBFactory     daoFactory;
    DAODBAccessKey           daoAccessKey;
    
    public DAOAccessKeyTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
        daoFactory = new DALDBFactory(DALDBFactory.Mode.Test);
        daoAccessKey = daoFactory.getDAOAccessKey();
        
        testStore();
        
    }
    
    @After
    public void tearDown() {
    }

    long   accessKeyId;
    String accessKeyString;
    
    /**
     * PlatformTest of store method, of class DAODBAccessKey.
     */
    
    public void testStore() {
        
        logger.log(Level.INFO, "Storing...");
        
        PrivateAccessKey accessKey = new PrivateAccessKey();
        daoAccessKey.store(accessKey);
        
        logger.log(Level.INFO, "Access Key is: {0}", accessKey.toString());
        
        accessKeyId = accessKey.getId();
        accessKeyString =   accessKey.getAccessKey();
        
        logger.log(Level.INFO, "Stored.");
        
        assertEquals(true, true);
    }

    /**
     * PlatformTest of retrieveById method, of class DAODBAccessKey.
     */
    @Test
    public void testRetrieveById() {

        logger.log(Level.INFO, "Retrieving by Id... ");
        
        PrivateAccessKey accessKey = new PrivateAccessKey(accessKeyId);
        
        accessKey = daoAccessKey.retrieveById( accessKey );
        
        logger.log(Level.INFO, "Retrieved.");
        
        assertEquals(accessKey != null, true);
    }

    /**
     * PlatformTest of retrieveByKey method, of class DAODBAccessKey.
     */
    @Test
    public void testRetrieveByKey() {

        logger.log(Level.INFO, "Retrieving by Key...");
        
        PrivateAccessKey accessKey = new PrivateAccessKey(accessKeyString);
        
        accessKey = daoAccessKey.retrieveByKey(accessKey);
        
        logger.log(Level.INFO, "Retrieved.");
        
        assertEquals(accessKey != null, true);
        
    }

    /**
     * PlatformTest of finalize method, of class DAODBAccessKey.
     */
    @Test
    public void testFinalize() throws Exception {

        daoAccessKey = null;
        daoFactory = null;
        
    }
}
