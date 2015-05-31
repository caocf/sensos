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
 * Entity Definition
 * 
 * Logical name : CustomerOrderControlTransactionLineItem Physical name:
 * TR_LTM_CTL_CTORD A component of an OrderControlTransaction, recording a
 * change in state for a particular OrderLineItem, by an Operator.
 * 
 * @author sensos
 */
public class CustomerOrderControlTransactionLineItem {

	/**
	 * TransactionID (PK)(FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) CustomerOrderControlTransaction (TR_CTL_CTORD)
	 */
	String transactionID;

	/**
	 * CustomerOrderID (FK) A unique system assigned identifier for the
	 * CustomerOrder that is being changed by the transaction line item. ID_ORD
	 * Identity integer CustomerOrderLineItem (DO_CTOLTM)
	 */
	Long customerOrderID;

	/**
	 * CustomerOrderSequenceNumber (FK) A unique system assigned identifier for
	 * the LineItem from the CustomerOrder that is being changed by the
	 * transaction line item. IC_OR_LTM LineNumber smallint CustomerOrderLineItem
	 * (DO_CTOLTM)
	 */
	int customerOrderSequenceNumber;

	/**
	 * AddressID (FK) A unique system allocated identifier for the Address.
	 * ID_ADS Identity integer Address (LO_ADS)
	 */
	long addressID;

	/**
	 * NewItemID (FK) A unique system assigned identifier for the retailer's
	 * SKU. ID_ITM IdentityUUID char(32) Item (AS_ITM)
	 */
	long newItemID;

	/**
	 * NewOrderLineItemStateCode (FK) A unique retailer assigned code denoting a
	 * the state for the CustomerOrderLineItem being changed by the transaction
	 * line item. CD_STE_CTOLM Code2Status char(2) CustomerOrderLineItemState
	 * (CO_STE_CTOLM)
	 **/
	String newOrderLineItemStateCode;

	/**
	 * NewQuantityOrdered The quantity of ordered Items, that's being assigned
	 * to the customer order by the transaction line item. QU_ORD Quantity
	 * DECIMAL(9,2)
	 */
	double newQuantityOrdered;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public Long getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(Long customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public int getCustomerOrderSequenceNumber() {
		return customerOrderSequenceNumber;
	}

	public void setCustomerOrderSequenceNumber(int customerOrderSequenceNumber) {
		this.customerOrderSequenceNumber = customerOrderSequenceNumber;
	}

	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}

	public long getNewItemID() {
		return newItemID;
	}

	public void setNewItemID(long newItemID) {
		this.newItemID = newItemID;
	}

	public String getNewOrderLineItemStateCode() {
		return newOrderLineItemStateCode;
	}

	public void setNewOrderLineItemStateCode(String newOrderLineItemStateCode) {
		this.newOrderLineItemStateCode = newOrderLineItemStateCode;
	}

	public double getNewQuantityOrdered() {
		return newQuantityOrdered;
	}

	public void setNewQuantityOrdered(double newQuantityOrdered) {
		this.newQuantityOrdered = newQuantityOrdered;
	}

}
