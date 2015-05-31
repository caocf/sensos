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
 * Logical name : CustomerAccountInvoice Physical name: DO_INVC_CTAC The record
 * of an invoice that was sent to the Customer, summarizing transactions charged
 * to and payments made against a particular CustomerAccount.
 * 
 * @author sensos
 *
 */
public class CustomerAccountInvoice {

	/**
	 * CustomerAccountInvoiceID (PK) A unique system assigned identifier for the
	 * CustomerAccountInvoice ID_INVC_CTAC Identity integer
	 */
	long customerAccountInvoiceID;

	/**
	 * ReportingPeriodID (FK) The unique system assigned identifier for a
	 * particular ReportingPeriod. ID_PRD_RP Identity integer ReportingPeriod
	 * (CA_PRD_RP)
	 */
	int reportingPeriodID;

	/**
	 * CustomerAccountID (FK) A unique identifier for a customer account.
	 * ID_CTAC Identity integer CustomerAccount (LE_CTAC)
	 */
	long customerAccountID;

	/**
	 * InvoiceContent An electronic copy of the CustomerAccountInvoice. This
	 * element may contain PDF file image, bitmap image, or HTML source for the
	 * invoice. BM_INVC_CNTT Image varbinary
	 */
	String invoiceContent;

	/**
	 * InvoiceTotalAmount The total monetary value of the
	 * CustomerAccountInvoice. MO_INVC_TOT MoneyShortRetail decimal(7,2)
	 */
	double invoiceTotalAmount;

	/**
	 * PaymentReceivedAmount The total value of all payments received against
	 * this CustomerAccountInvoice. MO_AMT_PYMT_RCV MoneyShortRetail
	 * decimal(7,2)
	 */
	double paymentReceivedAmount;

	public CustomerAccountInvoice(long customerAccountInvoiceID) {
		this.customerAccountInvoiceID = customerAccountInvoiceID;
	}

	public long getCustomerAccountInvoiceID() {
		return customerAccountInvoiceID;
	}

	public void setCustomerAccountInvoiceID(long customerAccountInvoiceID) {
		this.customerAccountInvoiceID = customerAccountInvoiceID;
	}

	public int getReportingPeriodID() {
		return reportingPeriodID;
	}

	public void setReportingPeriodID(int reportingPeriodID) {
		this.reportingPeriodID = reportingPeriodID;
	}

	public long getCustomerAccountID() {
		return customerAccountID;
	}

	public void setCustomerAccountID(long customerAccountID) {
		this.customerAccountID = customerAccountID;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public double getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	public void setInvoiceTotalAmount(double invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}

	public double getPaymentReceivedAmount() {
		return paymentReceivedAmount;
	}

	public void setPaymentReceivedAmount(double paymentReceivedAmount) {
		this.paymentReceivedAmount = paymentReceivedAmount;
	}

}
