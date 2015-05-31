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
 * Logical name: SaleReturnTaxLineItem Physical name: TR_LTM_SLS_RTN_TX
 * 
 * A Line Item to record taxation implications of a single SaleOrReturnLineItem
 * rather than an entire RetailTransaction
 * 
 * @author sensos
 *
 */
public class SaleReturnTaxLineItem {

	/**
	 * TransactionID (PK)(FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) SaleReturnLineItem (TR_LTM_SLS_RTN)
	 */
	String transactionID;

	/**
	 * RetailTransactionLineItemSequenceNumber (PK)(FK) The sequence number of
	 * line item within the context of this RetailTransaction. IC_LN_ITM
	 * LineNumber smallint SaleReturnLineItem (TR_LTM_SLS_RTN)
	 */
	String retailTransactionLineItemSequenceNumber;

	/**
	 * TaxGroupRuleID (FK) System assigned unique identifier for the
	 * TaxGroupRule. ID_RU_TX_GRP Identity integer TaxGroupRule (RU_TX_GP)
	 */
	int taxGroupRuleID;

	/**
	 * TaxPercent The percentage of the taxable portion of the taxable amount
	 * that is being collected as tax by this LineItem. PE_TX Percent
	 * decimal(7,4)
	 */
	double taxPercent;

	/**
	 * TaxAmount The monetary value tax that is being collected by this
	 * LineItem. MO_TX_RTN_SLS Money decimal(16,5)
	 */
	double taxAmount;

	/**
	 * ReasonCode (FK) A unique retailer defined reason code for an action that
	 * is taken (or not taken) at a Workstation. e.g. Return reason codes - Past
	 * Use By Date, Defective Merchandise, etc. POSNoSale reason Codes - Customer
	 * Change Query CD_RSN Code varchar(20) Reason (CO_CD_RSN)
	 */
	String reasonCode;

	/**
	 * TaxIncludedInPricesFlag A flag denoting that all applicable taxes are
	 * included in the prices for this line item, and that therefore this
	 * SaleReturnTaxLineItem is merely a summary of those taxes, FL_TX_INCL Flag
	 * integer
	 */
	int taxIncludedInPricesFlag;

	/**
	 * TaxableAmount The monetary amount for which tax is applicable.
	 * MO_TXBL_RTN_SLS Money decimal(16,5)
	 */
	double taxableAmount;

	/**
	 * TaxAtSourceFlag A flag denoting that the goods being sold and shipped
	 * have been taxed according to the TaxAuthorityShippingRule. FL_TX_SRC Flag
	 * integer
	 */
	int taxAtSourceFlag;

	/**
	 * TaxablePercent The percentage of the taxable amount that is liable for
	 * tax. Usually this is 100% but where an aggregate item contains taxed and
	 * non-taxed goods this value may be less than 100%. PE_TXBL Percent
	 * decimal(7,4)
	 */
	double taxablePercent;

	/**
	 * UsageCode (FK) Retailer defined mnemonic code identifying the
	 * CustomerIntendedUsage. Eg: EatIn, TakeOut CD_TYP_TX_INTD_USE Code4
	 * char(4) TaxIntendedUsageType (CO_TYP_TX_INTD_USE)
	 */
	String usageCode;

	public SaleReturnTaxLineItem(String transactionID,
			String retailTransactionLineItemSequenceNumber) {
		this.transactionID = transactionID;
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getRetailTransactionLineItemSequenceNumber() {
		return retailTransactionLineItemSequenceNumber;
	}

	public void setRetailTransactionLineItemSequenceNumber(
			String retailTransactionLineItemSequenceNumber) {
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
	}

	public int getTaxGroupRuleID() {
		return taxGroupRuleID;
	}

	public void setTaxGroupRuleID(int taxGroupRuleID) {
		this.taxGroupRuleID = taxGroupRuleID;
	}

	public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public int getTaxIncludedInPricesFlag() {
		return taxIncludedInPricesFlag;
	}

	public void setTaxIncludedInPricesFlag(int taxIncludedInPricesFlag) {
		this.taxIncludedInPricesFlag = taxIncludedInPricesFlag;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public int getTaxAtSourceFlag() {
		return taxAtSourceFlag;
	}

	public void setTaxAtSourceFlag(int taxAtSourceFlag) {
		this.taxAtSourceFlag = taxAtSourceFlag;
	}

	public double getTaxablePercent() {
		return taxablePercent;
	}

	public void setTaxablePercent(double taxablePercent) {
		this.taxablePercent = taxablePercent;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}

}
