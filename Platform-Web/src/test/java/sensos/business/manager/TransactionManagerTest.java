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
package sensos.business.manager;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sensos.bo.om.Customer;
import sensos.bo.om.RetailTransaction;
import sensos.bo.om.RetailTransactionLineItem;
import sensos.bo.om.Transaction;
import sensos.contracts.business.ITransactionManager;

/**
 *
 * @author sensos
 */
public class TransactionManagerTest {

    static Properties p = new Properties();

    static EJBContainer container = null;

    public TransactionManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();

    }

    @AfterClass
    public static void tearDownClass() {

        try {
        if (container != null) {
            container.close();
        }
        } catch (Exception e) {
            ;
        }
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of createTransaction method, of class TransactionManager.
     */
    @Test
    public void testCreateTransaction() throws Exception {
        System.out.println("createTransaction");

        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        Transaction expResult = null;
        Transaction result = instance.createTransaction();
        assertEquals(true, true);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of beginTransaction method, of class TransactionManager.
     */
    @Test
    public void testBeginTransaction() throws Exception {
        System.out.println("beginTransaction");
        Transaction t = null;

        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        instance.beginTransaction(t);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of commitTransaction method, of class TransactionManager.
     */
    @Test
    public void testCommitTransaction() throws Exception {
        System.out.println("commitTransaction");
        Transaction t = null;
        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        instance.commitTransaction(t);
        // container.close();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of rollbackTransaction method, of class TransactionManager.
     */
    @Test
    public void testRollbackTransaction() throws Exception {
        System.out.println("rollbackTransaction");
        Transaction t = null;
        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        instance.rollbackTransaction(t);
       // container.close();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of createRetailTransaction method, of class TransactionManager.
     */
    @Test
    public void testCreateRetailTransaction_0args() throws Exception {
        System.out.println("createRetailTransaction");
        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        RetailTransaction expResult = null;
        RetailTransaction result = instance.createRetailTransaction();
        assertEquals(true, true);
        //container.close();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of createRetailTransaction method, of class TransactionManager.
     */
    @Test
    public void testCreateRetailTransaction_Customer() throws Exception {
        System.out.println("createRetailTransaction");
        Customer c = new Customer();
        c.setCustomerId(1L);
        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        RetailTransaction expResult = null;
        RetailTransaction result = instance.createRetailTransaction(c);
        assertEquals(true, true);
       // container.close();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of createRetailTransactionLineItem method, of class
     * TransactionManager.
     */
    @Test
    public void testCreateRetailTransactionLineItem() throws Exception {
        System.out.println("createRetailTransactionLineItem");
        RetailTransaction t = null;
        ITransactionManager instance = (ITransactionManager) container.getContext().lookup("java:global/Platform-Web/TransactionManager");
        RetailTransactionLineItem expResult = null;
        RetailTransactionLineItem result = instance.createRetailTransactionLineItem(t);
        assertEquals(true, true);
       // container.close();
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
