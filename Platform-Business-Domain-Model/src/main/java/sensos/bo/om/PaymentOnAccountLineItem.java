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
 * Logical name : PaymentOnAccountLineItem Physical name: TR_LTM_PYAN A detail
 * line item recording a payment for a product or service purchased on an
 * installment account basis. These line items are different from those charged
 * to a house account. In this case the account normally has to be completely
 * paid off within a specific time period. The account exists only until the
 * installment sale is completely extinguished.
 * 
 * @author sensos
 *
 */
public class PaymentOnAccountLineItem extends RetailTransactionLineItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8413458339222684821L;

	public PaymentOnAccountLineItem(String transactionID,
			int retailTransactionLineItemSequenceNumber,
			CustomerOrderLineItem customerOrderLineItem) {
		super(transactionID, retailTransactionLineItemSequenceNumber,
				customerOrderLineItem);
	}

	/**
	 * CustomerAccountID (FK) A unique identifier for a customer account.
	 * ID_CTAC Identity integer CustomerAccount (LE_CTAC)
	 */
	long customerAccountID;

	/**
	 * CustomerAccountCardID (FK) A 16 digit Credit or Debit card PAN as defined
	 * by ISO-7812, uniquely identifying CustomerAccountCard. AI_ACNT_CT_CRD
	 * CreditDebitCardPAN varchar(20) CustomerAccountCard (LE_ACNT_CT_CRD)
	 */
	String customerAccountCardID;

	/**
	 * CustomerAccountInvoiceID (FK) A unique system assigned identifier for the
	 * CustomerAccountInvoice ID_INVC_CTAC Identity integer
	 * CustomerAccountInvoice (DO_INVC_CTAC)
	 */
	int customerAccountInvoiceID;

	/**
	 * AmountReceived The monetary payment on account received from the Customer
	 * MO_PYM_AGT_RCV Money decimal(16,5)
	 */
	double amountReceived;

	/**
	 * BalanceDue The monetary amount due on the CustomerAccount after the
	 * payment is processed - as calculated by the POS Application and printed
	 * on the transaction receipt. This is a transaction snapshot of the balance
	 * plus accured interest in CustomerAccount which is the on-going balance.
	 * MO_BLNC MoneyShortRetail decimal(7,2)
	 */
	double balanceDue;

	public long getCustomerAccountID() {
		return customerAccountID;
	}

	public void setCustomerAccountID(long customerAccountID) {
		this.customerAccountID = customerAccountID;
	}

	public String getCustomerAccountCardID() {
		return customerAccountCardID;
	}

	public void setCustomerAccountCardID(String customerAccountCardID) {
		this.customerAccountCardID = customerAccountCardID;
	}

	public int getCustomerAccountInvoiceID() {
		return customerAccountInvoiceID;
	}

	public void setCustomerAccountInvoiceID(int customerAccountInvoiceID) {
		this.customerAccountInvoiceID = customerAccountInvoiceID;
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}

}
