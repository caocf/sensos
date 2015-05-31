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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import sensos.bo.om.Customer;
import sensos.bo.om.CustomerContact;
import sensos.contracts.business.IBillingManager;
import sensos.dal.billing.DAODBCustomer;

/**
 * Implementation to interact with billing components
 *
 * @see sensos.contracts.business.IBillingManager
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(IBillingManager.class)
@DependsOn("Manager")
public class BillingManager implements IBillingManager {

    private static DAODBCustomer daoCustomer;
    
    @PostConstruct
    public void init() {
        daoCustomer = Manager.factoryBilling.getDAOCustomer();
    }
    
    @Override
    @Asynchronous
    public void createCustomer(Customer customer) {
        daoCustomer.store(customer);
    }

    @Override
    @Asynchronous
    public void disableCustomer(Customer customer) {
        daoCustomer.disable(customer);
    }
    
    @Override
    public Customer getCustomer(Customer customer) {
        return daoCustomer.getCustomer(customer);
    }

    @Override
    @Asynchronous
    public void updateCustomer(Customer customer) {
        daoCustomer.update(customer);
    }

    @Override
    @Asynchronous
    public void createCustomerContact(CustomerContact contact) {
        daoCustomer.store(contact);
    }

    @Override
    @Asynchronous
    public void updateCustomerContact(CustomerContact contact) {
        daoCustomer.update(contact);
    }

    @Override
    public List<CustomerContact> getCustomerContact(Customer customer) {
        return daoCustomer.getCustomerContact(customer);
    }
    
}
