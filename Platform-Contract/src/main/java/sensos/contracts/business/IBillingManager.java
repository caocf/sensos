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
package sensos.contracts.business;

import java.util.List;

import javax.ejb.Remote;

import sensos.bo.om.Customer;
import sensos.bo.om.CustomerContact;

/**
 * Interacts with billing system that acts as a main repository for Customer information.
 * 
 * @see sensos.bo.om.Customer
 * @see sensos.bo.om.CustomerContact
 * 
 * @author sensos
 */
@Remote
public interface IBillingManager {
   
    /**
     * Create customer into the billing system.
     * @param customer
     */
    public void createCustomer(Customer customer);
    
    /**
     * Disable customer at least from billing system.
     * @param customer
     */
    public void disableCustomer(Customer customer);
    
    /**
     * Update customer in the billing system.
     * @param customer
     */
    public void updateCustomer(Customer customer);
    
    /**
     * Get customer from the billing system
     * @param customer
     * @return 
     */
    public Customer getCustomer(Customer customer);
    
    /**
     * Create customer contact details into the billing system.
     * @param contact
     */
    public void createCustomerContact(CustomerContact contact);
    
    /**
     * Update customer contact details into the billing system.
     * @param contact
     */
    public void updateCustomerContact(CustomerContact contact);
    
    /**
     * Get customer contact details from the billing system.
     * @param contact
     * @return 
     */
    public List<CustomerContact> getCustomerContact(Customer customer);
    
}
