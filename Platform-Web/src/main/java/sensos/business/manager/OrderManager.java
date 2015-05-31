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

import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import sensos.bo.om.Customer;
import sensos.bo.om.CustomerOrder;
import sensos.bo.om.CustomerOrderProductLineItem;
import sensos.contracts.business.IOrderManager;

@Singleton
@Startup
@Remote(IOrderManager.class)//@DependsOn("Manager")
public class OrderManager implements IOrderManager {

	@Override
	public CustomerOrder createCustomerOrder() {
		
		CustomerOrder c = new CustomerOrder();
		
		return c;
		
	}

	@Override
	public CustomerOrder createCustomerOrder(Customer customer) {
		
		CustomerOrder c = new CustomerOrder(customer);
		
		return c;
	}


	@Override
	public CustomerOrderProductLineItem createCustomerOrderProductLineItem(
			CustomerOrder customerOrder) {

		if(customerOrder == null)
			throw new IllegalStateException("Incoming customer order cannot be null.");
		
		Long ord = customerOrder.getCustomerOrderID();
		int  seq = customerOrder.getCustomerOrderLineItemCount() + 1; 
				
		
		CustomerOrderProductLineItem col = new CustomerOrderProductLineItem(ord, seq);
		
		return col;


	}
	
}
