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
 * Logical name : TaxLineItem Physical name: TR_LTM_TX
 * 
 * A line item component of a RetailTransaction that records the charging and
 * offsetting liability credit for sales tax on merchandise items and services
 * sold by the store or debit for merchandise returned to the store.
 *
 */
public class TaxLineItem extends RetailTransactionLineItem {

	public TaxLineItem(String transactionID,
			int retailTransactionLineItemSequenceNumber,
			CustomerOrderLineItem customerOrderLineItem) {
		super(transactionID, retailTransactionLineItemSequenceNumber,
				customerOrderLineItem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * TaxAmount The monetary amount of sales Tax calculated by applying the
	 * appropriate TaxGroupRule and TaxRateRule. MO_TX Money decimal(16,5)
	 */
	double taxAmount;

	/**
	 * TaxPercent The percentage of the taxable amount that should is tax.
	 * Derived by applying the appropriate TaxGroupRule and TaxRateRule. PE_TX
	 * Percent decimal(7,4)
	 */
	double taxPercent;

	/**
	 * ReasonCode (FK) A unique retailer defined reason code for an action that
	 * is taken (or not taken) at a Workstation. e.g. Return reason codes - Past
	 * Use By Date, Defective Merchandise, etc. POSNoSale reason Codes -
	 * Customer Change Query CD_RSN Code varchar(20) Reason (CO_CD_RSN)
	 */
	String reasonCode;

	/**
	 * TaxableAmount The monetary value of the transaction for which tax is
	 * being calculated. MO_TXBL Money decimal(16,5)
	 */
	double taxableAmount;

	/**
	 * ExceptionalTaxRuleFlag A flag denoting that a special TaxGroupRule has
	 * been invoked rather than the normal one. FL_TX_RU_EXC Flag integer
	 */
	int exceptionalTaxRuleFlag;

	/**
	 * TaxIncludedInPricesFlag A flag denoting that all applicable taxes are
	 * included in the prices for this line item, and that therefore this
	 * SaleReturnTaxLineItem is merely a summary of those taxes, FL_TX_INCL_PR
	 * Flag integer
	 */
	int taxIncludedInPricesFlag;

	/**
	 * TaxAtSourceFlag A flag denoting that the goods being sold and shipped
	 * have been taxed according to the TaxAuthorityShippingRule. FL_TX_SRC Flag
	 * integer
	 */
	int taxAtSourceFlag;

	/**
	 * TaxablePercent The percentage of the taxable amount that is liable for
	 * tax. Usually this is 100% but where an aggregate item contains taxed and
	 * non-taxed goods this value may be less than 100%. PE_TXBLE Percent
	 * decimal(7,4)
	 */
	double taxablePercent;

	/**
	 * TaxGroupRuleID (FK) System assigned unique identifier for the
	 * TaxGroupRule. ID_RU_TX_GRP Identity integer TaxGroupRule (RU_TX_GP)
	 */
	int taxGroupRuleID;

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public int getExceptionalTaxRuleFlag() {
		return exceptionalTaxRuleFlag;
	}

	public void setExceptionalTaxRuleFlag(int exceptionalTaxRuleFlag) {
		this.exceptionalTaxRuleFlag = exceptionalTaxRuleFlag;
	}

	public int getTaxIncludedInPricesFlag() {
		return taxIncludedInPricesFlag;
	}

	public void setTaxIncludedInPricesFlag(int taxIncludedInPricesFlag) {
		this.taxIncludedInPricesFlag = taxIncludedInPricesFlag;
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

	public int getTaxGroupRuleID() {
		return taxGroupRuleID;
	}

	public void setTaxGroupRuleID(int taxGroupRuleID) {
		this.taxGroupRuleID = taxGroupRuleID;
	}

}
