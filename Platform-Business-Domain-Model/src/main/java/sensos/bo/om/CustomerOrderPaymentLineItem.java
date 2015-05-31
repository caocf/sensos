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
 * A line item component of a Customer placed order, recording any deposit
 * payment that is made during the life cycle of the Order. The actual payment
 * will be recorded as a PaymentOnAccount line item in a RetailTransaction, and
 * will be accrued in a CustomerAccount, until the ordered merchandise is
 * shipped or picked up.
 * 
 * @author sensos
 *
 */
public class CustomerOrderPaymentLineItem extends CustomerOrderLineItem {

	/**
	 * TransactionID (FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) PaymentOnAccountLineItem (TR_LTM_PYAN)
	 */
	String transactionID;

	/**
	 * RetailTransactionLineItemSequenceNumber (FK) The sequence number of line
	 * item within the context of this RetailTransaction. IC_LN_ITM LineNumber
	 * smallint PaymentOnAccountLineItem (TR_LTM_PYAN)
	 */
	int retailTransactionLineItemSequenceNumber;

	public CustomerOrderPaymentLineItem(long customerOrderID,
			int customerOrderSequenceNumber) {
		super(customerOrderID, customerOrderSequenceNumber);
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

}
