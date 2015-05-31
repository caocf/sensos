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

import java.io.Serializable;
import java.sql.Date;

/**
 * A detail line item of a RetailTransaction that records the business conducted
 * between the retail store and another party involving the exchange in
 * ownership and/or accountability for merchandise and/or tender or involving
 * the exchange of tender for services.
 * 
 * @author sensos
 *
 */
public class RetailTransactionLineItem extends SaleReturnLineItem implements
		Serializable {

	/**
	 * RetailTransactionLineItemSequenceNumber (PK) The sequence number of line
	 * item within the context of this RetailTransaction. IC_LN_ITM LineNumber
	 * smallint
	 */
	int retailTransactionLineItemSequenceNumber;

	/**
	 * TransactionID (PK)(FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) RetailTransaction (TR_RTL)
	 */
	String transactionID;

	/**
	 * CustomerOrderID (FK) A unique system assigned identifier for the
	 * CustomerOrder. ID_ORD Identity integer CustomerOrderLineItem (DO_CTOLTM)
	 */
	int customerOrderID;

	/**
	 * CustomerOrderSequenceNumber (FK) A unique system assigned identifier for
	 * the LineItem within the confines of the CustomerOrder. IC_OR_LTM
	 * LineNumber smallint CustomerOrderLineItem (DO_CTOLTM)
	 */
	int customerOrderSequenceNumber;

	/**
	 * BeginDateTimestamp The start time of the RETAIL TRANSACTION line item.
	 * TS_LN_ITM_BGN EffectiveDateTime datetime
	 */
	Date beginDateTimestamp;

	/**
	 * EndDateTimestamp The end time of the RETAIL TRANSACTION line item.
	 * TS_LN_ITM_END ExpirationDateTime datetime
	 */
	Date endDateTimestamp;

	/**
	 * EntryMethodCode A retailer assigned code to denote how the
	 * RetailTransactionLineItem was entered at the Workstation.
	 * LU_MTH_LTM_RTL_TRN EntryMethodCode varchar(20)
	 */
	String entryMethodCode;

	/**
	 * RetailTransactionLineItemTypeCode (FK) A code to denote the type of
	 * retail transaction line item, such as Sale/Return, Void, miscellaneous
	 * fee, etc. Valid values include: SALERETURN - merchandise/service sale
	 * return TENDER - tender line item PRC_MOD - price modifier (discount)
	 * GIFTCERT - gift certificate (stored value SALE) DEPSTREM - Deposit
	 * redemption on cans, etc MISCFEE - misc. fee line item PAYMTONACT -
	 * Payment on account line item records credit against customer AR WRTOFF -
	 * Writeoff line item to debt out incomplete trns (drive off) RNDG -
	 * Rounding line item LYLTY_PROM - Customer loyalty program bonus pts earned
	 * as part of a promotion. CD_TYP_LTM_TRN_RTL Code2 char(2)
	 * RetailTransactionLineItemType (CO_TYP_LTM_TRN_RTL)
	 */
	String retailTransactionLineItemTypeCode;

	/**
	 * VoidFlag :A boolen indicator that tells if this line item is VOIDED or
	 * not. A line item may be voided in place or voided by reference. Voiding
	 * in place means that the line item is negated without requiring a separate
	 * void line item to reverse it (hence the term in place). Voiding by
	 * reference means that the item is voided by a second void line item that
	 * reverses it. So the voided line item is supplemented by a voiding line
	 * item (by reference). Voiding by reference is currently handled through an
	 * optional VoidsLineItem entity type. Because the relationship is optional
	 * we can represent voiding in place and voiding by reference. While this
	 * works it needs to be modified. As modeled, this attribute is NOT A
	 * COMPLETE treatment of void and cancellation. It will be modifed in the
	 * next data model version. . FL_VD_LN_ITM Flag integer
	 **/
	boolean voidFlag;

	/**
	 * RetailTransactionLineItemEntryModeCode A code that designates the
	 * privlege mode or level that the individual creating this transaction
	 * used. The purpose is to track illicit uses of manager and maintenance
	 * modes that might be used to circumvent normal operational and financial
	 * controls Sample values: NORMAL - lowest privilege level for cashiers
	 * MANAGER - intermeidate privilege level fo rmanagers MAINTENANCE - highest
	 * leve privilege for auditors, tax authorities and authorized POS
	 * maintenance indiviuals. CD_RTL_TRN_LN_ITM_ENR_MOD Code varchar(20)
	 */
	String retailTransactionLineItemEntryModeCode;

	/**
	 * Has always at least one
	 */
	CustomerOrderLineItem customerOrderLineItem;

	public RetailTransactionLineItem(String transactionID,
			int retailTransactionLineItemSequenceNumber,
			CustomerOrderLineItem customerOrderLineItem) {

		this.transactionID = transactionID;
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
		this.customerOrderLineItem = customerOrderLineItem;

	}

	public int getRetailTransactionLineItemSequenceNumber() {
		return retailTransactionLineItemSequenceNumber;
	}

	public void setRetailTransactionLineItemSequenceNumber(
			int retailTransactionLineItemSequenceNumber) {
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(int customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public int getCustomerOrderSequenceNumber() {
		return customerOrderSequenceNumber;
	}

	public void setCustomerOrderSequenceNumber(int customerOrderSequenceNumber) {
		this.customerOrderSequenceNumber = customerOrderSequenceNumber;
	}

	public Date getBeginDateTimestamp() {
		return beginDateTimestamp;
	}

	public void setBeginDateTimestamp(Date beginDateTimestamp) {
		this.beginDateTimestamp = beginDateTimestamp;
	}

	public Date getEndDateTimestamp() {
		return endDateTimestamp;
	}

	public void setEndDateTimestamp(Date endDateTimestamp) {
		this.endDateTimestamp = endDateTimestamp;
	}

	public String getEntryMethodCode() {
		return entryMethodCode;
	}

	public void setEntryMethodCode(String entryMethodCode) {
		this.entryMethodCode = entryMethodCode;
	}

	public String getRetailTransactionLineItemTypeCode() {
		return retailTransactionLineItemTypeCode;
	}

	public void setRetailTransactionLineItemTypeCode(
			String retailTransactionLineItemTypeCode) {
		this.retailTransactionLineItemTypeCode = retailTransactionLineItemTypeCode;
	}

	public boolean isVoidFlag() {
		return voidFlag;
	}

	public void setVoidFlag(boolean voidFlag) {
		this.voidFlag = voidFlag;
	}

	public String getRetailTransactionLineItemEntryModeCode() {
		return retailTransactionLineItemEntryModeCode;
	}

	public void setRetailTransactionLineItemEntryModeCode(
			String retailTransactionLineItemEntryModeCode) {
		this.retailTransactionLineItemEntryModeCode = retailTransactionLineItemEntryModeCode;
	}

	public CustomerOrderLineItem getCustomerOrderLineItem() {
		return customerOrderLineItem;
	}

	public void setCustomerOrderLineItem(
			CustomerOrderLineItem customerOrderLineItem) {
		this.customerOrderLineItem = customerOrderLineItem;
	}

}
