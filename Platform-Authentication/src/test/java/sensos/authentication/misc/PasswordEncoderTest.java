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

import sensos.authentication.misc.PasswordEncoder;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test password encoding and decoding.
 * @author sensos
 */
public class PasswordEncoderTest {
    
    public PasswordEncoderTest() {
    }

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

    /**
     * Test of getInstance method, of class PasswordEncoder.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PasswordEncoder result = PasswordEncoder.getInstance();
        assertTrue(result!=null);
    }

    /**
     * Test of encode method, of class PasswordEncoder.
     */
    @Test
    public void testEncode() throws Exception {
        System.out.println("encode");
        String password = "password";
        String saltKey = "saltkey";
        PasswordEncoder instance = PasswordEncoder.getInstance();
        String expResult = "";
        String result = instance.encode(password, saltKey);
        String result2 = instance.encode(password, saltKey);
        
        
        assertTrue(result.equals(result2));
      }
}
