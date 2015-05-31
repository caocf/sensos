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
 * Logical name : CustomerOrderTenderPreAuthorization Physical name:
 * DO_CTOLTM_TND_AZN
 * 
 * A line item component of a Customer placed order, recording any Credit or
 * Debit Card pre-authorization that is performed at the time the order is
 * taken.
 * 
 * @author sensos
 *
 */
public class CustomerOrderTenderPreAuthorization extends CustomerOrderLineItem {

	/**
	 * CustomerOrderID (PK)(FK) A unique system assigned identifier for the
	 * CustomerOrder. ID_ORD Identity integer CustomerOrderLineItem (DO_CTOLTM)
	 */
	/**
	 * CustomerOrderSequenceNumber (PK)(FK) A unique system assigned identifier
	 * for the LineItem within the confines of the CustomerOrder. IC_OR_LTM
	 * LineNumber smallint CustomerOrderLineItem (DO_CTOLTM)
	 */
	/**
	 * TransactionID (FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) TenderAuthorization (CO_AZN_TND)
	 */
	String transactionID;
	/**
	 * RetailTransactionLineItemSequenceNumber (FK) The sequence number of line
	 * item within the context of this RetailTransaction. IC_LN_ITM LineNumber
	 * smallint TenderAuthorization (CO_AZN_TND)
	 */
	int retailTransactionLineItemSequenceNumber;

	/**
	 * TenderAuthorizationSequenceNumber (FK) A unique sequence number for this
	 * TenderAuthorization. Required because a particular TenderLineItem may
	 * have more than one TenderAuthorization attempt. AI_TND_AZN LineNumber
	 * smallint TenderAuthorization (CO_AZN_TND)
	 */
	int tenderAuthorizationSequenceNumber;

	public CustomerOrderTenderPreAuthorization(long customerOrderID,
			int customerOrderSequenceNumber) {
		super(customerOrderID, customerOrderSequenceNumber);
		// TODO Auto-generated constructor stub
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

	public int getTenderAuthorizationSequenceNumber() {
		return tenderAuthorizationSequenceNumber;
	}

	public void setTenderAuthorizationSequenceNumber(
			int tenderAuthorizationSequenceNumber) {
		this.tenderAuthorizationSequenceNumber = tenderAuthorizationSequenceNumber;
	}

}
