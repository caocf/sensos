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

import javax.ejb.Remote;

import sensos.bo.om.Customer;
import sensos.bo.om.RetailTransaction;
import sensos.bo.om.RetailTransactionLineItem;
import sensos.bo.om.Transaction;

@Remote
public interface ITransactionManager {

	/**
	 * Create empty transaction
	 * @return
	 */
	public Transaction createTransaction();
	
	/**
	 * 
	 * @param t
	 */
	public void beginTransaction(Transaction t);
	
	/**
	 * 
	 * @param t
	 */
	public void commitTransaction(Transaction t);
	
	/**
	 * 
	 * @param t
	 */
	public void rollbackTransaction(Transaction t);
	
	/**
	 * Create empty retail transaction
	 * @return
	 */
	public RetailTransaction createRetailTransaction();
	
	/**
	 * Create retail transaction and assign a customer.
	 * @param c
	 * @return
	 */
	public RetailTransaction createRetailTransaction(Customer c);
	
	/**
	 * Create retail transaction lineitem for a transaction.
	 * 
	 * @param retailTransaction
	 * @param customerOrder
	 */
	public RetailTransactionLineItem createRetailTransactionLineItem(RetailTransaction t);
	
	
	
}
