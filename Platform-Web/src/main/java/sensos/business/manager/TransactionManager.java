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

import java.util.UUID;

import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import sensos.bo.om.Customer;
import sensos.bo.om.RetailTransaction;
import sensos.bo.om.RetailTransactionLineItem;
import sensos.bo.om.Transaction;
import sensos.contracts.business.ITransactionManager;

@Singleton
@Startup
@Remote(ITransactionManager.class)
public class TransactionManager implements ITransactionManager {

	@Override
	public Transaction createTransaction() {
	
		Transaction t = new Transaction(UUID.randomUUID().toString());
		
		return t;
		
	}

	@Override
	public void beginTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commitTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollbackTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RetailTransaction createRetailTransaction() {

		RetailTransaction rt = new RetailTransaction(UUID.randomUUID().toString());
		
		return rt;
		
	}

	@Override
	public RetailTransaction createRetailTransaction(Customer c) {
		
		if(c == null || c.getCustomerId() == null)
			throw new IllegalStateException("Incoming customer or customer id cannot be null.");
		
		RetailTransaction rt = this.createRetailTransaction();
		
		rt.setCustomerID(c.getCustomerId());
		
		return null;
		
	}

	@Override
	public RetailTransactionLineItem createRetailTransactionLineItem(RetailTransaction t) {
		 		
		return null;
	}

}
