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
 * 
 * A line item component of a Customer placed order.
 * 
 * @author sensos
 *
 */
public abstract class CustomerOrderLineItem {

	/**
	 * CustomerOrderID (PK)(FK) A unique system assigned identifier for the
	 * CustomerOrder. ID_ORD Identity integer CustomerOrder (DO_CTORD)
	 **/
	long customerOrderID;

	/**
	 * CustomerOrderSequenceNumber (PK) A unique system assigned identifier for
	 * the LineItem within the confines of the CustomerOrder. IC_OR_LTM
	 * LineNumber smallint
	 **/
	int customerOrderSequenceNumber;

	/**
	 * TransactionID (FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) RetailTransactionLineItem (TR_LTM_RTL_TRN)
	 **/
	String transactionID;

	/**
	 * RetailTransactionLineItemSequenceNumber (FK) The sequence number of line
	 * item within the context of this RetailTransaction. IC_LN_ITM LineNumber
	 * smallint RetailTransactionLineItem (TR_LTM_RTL_TRN)
	 **/
	int retailTransactionLineItemSequenceNumber;

	/**
	 * RelativeOrder A retailer assigned relative order number, showing the
	 * relative order of this LineItem with respect to the other LineItems in
	 * the CustomerOrder. This allows the retailer to modifiy the order of
	 * LineITems within the context ofthe CustomerOrder - during the Order's
	 * life-cycle. IC_RL_ORD LineNumber smallint
	 */
	int relativeOrder;

	/**
	 * CustomerOrderLineItemTypeCode A retailer assigned code denoting which
	 * sub-type of CustomerOrderLineItem this particular line item is. TY_CTOLTM
	 * Code2 char(2)
	 */
	String customerOrderLineItemTypeCode;

	public CustomerOrderLineItem(long customerOrderID,
			int customerOrderSequenceNumber) {
		this.customerOrderID = customerOrderID;
		this.customerOrderSequenceNumber = customerOrderSequenceNumber;
	}

	public long getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(long customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public int getCustomerOrderSequenceNumber() {
		return customerOrderSequenceNumber;
	}

	public void setCustomerOrderSequenceNumber(int customerOrderSequenceNumber) {
		this.customerOrderSequenceNumber = customerOrderSequenceNumber;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getRetailTransactionLineItemSequenceNumber() {
		return retailTransactionLineItemSequenceNumber;
	}

	public void setRetailTransactionLineItemSequenceNumber(
			int retailTransactionLineItemSequenceNumber) {
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
	}

	public int getRelativeOrder() {
		return relativeOrder;
	}

	public void setRelativeOrder(int relativeOrder) {
		this.relativeOrder = relativeOrder;
	}

	public String getCustomerOrderLineItemTypeCode() {
		return customerOrderLineItemTypeCode;
	}

	public void setCustomerOrderLineItemTypeCode(
			String customerOrderLineItemTypeCode) {
		this.customerOrderLineItemTypeCode = customerOrderLineItemTypeCode;
	}

}
