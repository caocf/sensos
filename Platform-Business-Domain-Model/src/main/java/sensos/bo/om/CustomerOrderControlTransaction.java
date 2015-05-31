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

package sensos.bo.om;

/**
 * A type of Transaction recording the creation or alteration of a Customer
 * Order, by a particular Operator.
 * 
 * @author sensos
 *
 */
public class CustomerOrderControlTransaction {

	/**
	 * TransactionID (PK)(FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) Transaction (TR_TRN)
	 */
	String transactionID;

	/**
	 * CustomerOrderID (FK) A unique system assigned identifier for the
	 * CustomerOrder. ID_ORD Identity integer CustomerOrder (DO_CTORD)
	 */
	long customerOrderID;

	/**
	 * CustomerOrderStateCode (FK) A unique retailer assigned code denoting a
	 * potential state for a CustomerOrder. Eg: Create, DeleteItem, AddItem,
	 * ChangeItem, PartialDelivery, DeliveryComplete, PartialPickup,
	 * PickupComplete etc... CD_STE_CTORD Code2 char(2) CustomerOrderState
	 * (CO_STE_CTORD)
	 */
	String customerOrderStateCode;

	/**
	 * CustomerOrderTypeCode (FK) A unique retailer assigned code denoting a
	 * type of CustomerOrder Eg: Layaway, Order for Delivery, Order for Pickup,
	 * etc... CD_TYP_CTORD Code2 char(2) CustomerOrderType (CO_TYP_CTORD)
	 */
	String customerOrderTypeCode;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public long getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(long customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public String getCustomerOrderStateCode() {
		return customerOrderStateCode;
	}

	public void setCustomerOrderStateCode(String customerOrderStateCode) {
		this.customerOrderStateCode = customerOrderStateCode;
	}

	public String getCustomerOrderTypeCode() {
		return customerOrderTypeCode;
	}

	public void setCustomerOrderTypeCode(String customerOrderTypeCode) {
		this.customerOrderTypeCode = customerOrderTypeCode;
	}

}
