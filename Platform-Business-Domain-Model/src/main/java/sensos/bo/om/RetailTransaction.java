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
import java.util.Set;

/**
 * A type of Transaction that records the business conducted between the retail
 * enterprise and another party involving the exchange in ownership and/or
 * accountability for merchandise and/or tender or involving the exchange of
 * tender for services.
 * 
 * @author sensos
 *
 */
public class RetailTransaction extends Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5315357790759696985L;

	public RetailTransaction(String transactionID) {
		super(transactionID);

	}

	/**
	 * LocationID (FK) A unique system assigned identifier for the
	 * SellingLocation. ID_LCN Identity integer SellingLocation (LO_LCN_SL)
	 */
	int locationID;

	/**
	 * RetailTransactionTypeCode (FK) A retailer assigned code denoting a type
	 * of RetailTransaction. CD_TYP_TRN_RTL Code2 char(2) RetailTransactionType
	 * (CO_TYP_TRN_RTL)
	 */
	String retailTransactionTypeCode;

	/**
	 * RingElapsedTime The total time elapsed between commencement of the
	 * RetailTransaction and the commencement of the transaction tendering
	 * process. IN_RNG_ELPSD QuantityShortCountSeconds decimal(5,0)
	 */
	double ringElapsedTime;

	/**
	 * TillID (FK) The unique identifier for the TENDER RESPOSITORY.
	 * ID_RPSTY_TND Identity integer Till (AS_TL)
	 */
	int tillID;

	/**
	 * TenderElapsedTime The total time taken to receive all the tender and
	 * complete the RetailTransaction. IN_TND_ELPSD QuantityShortCountSeconds
	 * decimal(5,0)
	 */
	double tenderElapsedTime;

	/**
	 * The total time taken that a particular Workstation was idle between
	 * completion of the previous and commencement of the current
	 * RetailTransaction IN_ELPSD_IDL QuantityShortCountSeconds decimal(5,0)
	 */
	double idleElapsedTime;

	/**
	 * LockElapsedTime The total time the workstation is in lock mode, ie
	 * securement of the WORKSTATION to prevent misuse by another party, between
	 * the previous and current RetailTransactions. IN_LCK_ELPSD
	 * QuantityShortCountSeconds decimal(5,0)
	 */
	double lockElapsedTime;

	/**
	 * LineItemsScannedCount The total number of individual ITEMs which are
	 * scanned at the point of sale, in comparison to those keyed. QU_ITM_LN_SC
	 * QuantityTransactionCount decimal(7,0)
	 */
	double lineItemsScannedCount;

	/**
	 * LineItemsKeyedCount the total number of ITEMs which are keyed at the
	 * point of sale in comparison to scanned. QU_ITM_LN_KY
	 * QuantityTransactionCount decimal(7,0)
	 */
	double lineItemsKeyedCount;

	/**
	 * UnitCount The total number of individual ITEMs (SKU's) which are sold in
	 * the transaction QU_UN_RTL_TRN QuantityTransactionCount decimal(7,0)
	 */
	double unitCount;

	/**
	 * LineItemsScannedPercent The total number of individual ITEMs which are
	 * scanned at the point of sale, as a percentage of the total data capture
	 * time. PE_ITM_LN_SC Percent decimal(7,4)
	 */
	double lineItemsScannedPercent;

	/**
	 * LineItemsKeyedPercent The total number of individual ITEMs which are
	 * keyed at the point of sale, as a percentage of the total data capture
	 * time. PE_ITM_LN_KY Percent decimal(7,4)
	 */
	double lineItemsKeyedPercent;

	/**
	 * KeyDepartmentCount The total number of ITEMs which are keyed by
	 * department at the point of sale. QU_DPT_KY QuantityTransactionCount
	 * decimal(7,0)
	 */
	double keyDepartmentCount;

	/**
	 * KeyDepartmentPercent The total number of individual ITEMs which are keyed
	 * by department at the point of sale, as a percentage of the total data
	 * capture time. PE_DPT_KY Percent decimal(7,4)
	 */
	double keyDepartmentPercent;

	/**
	 * ReceiptDateTime The date and time that was printed on the receipt for
	 * this transaction. TS_TRN_RCP AuditDateTime datetime
	 */
	Date receiptDateTime;

	/**
	 * CustomerID (FK) A unique system assigned identifier for the Customer.
	 * ID_CT Identity integer Customer (PA_CT)
	 */
	long customerID;

	/**
	 * CustomerIDEntryMethodCode Standardized code denoting how the CustomerID
	 * was captured for this RetailTransaction. Examples include: SCANNED -
	 * scanned from loyalty card or other retailer issued customer ID KEYED -
	 * manually entered from loyalty card ALT_ID_PHONE - no card presented, used
	 * phone number as alternate ID CD_CT_ID_ENR_MTH EntryMethodCode varchar(20)
	 */
	String customerIDEntryMethodCode;

	/**
	 * ChannelID (FK) Token Identifier for a channel instance ID_CHNL Identity
	 * integer Channel (CO_CHNL)
	 */
	int channelID;

	/**
	 * RetailShoppingTripTypeCode (FK) A code designating the purpose of the
	 * shopping trip that is related to a RetailTransaction. Examples: QUICKSTOP
	 * FILLIN STOCKUP CD_RTL_SHPPG_TRP_TYP Code varchar(20)
	 * RetailShoppingTripType (CO_RTL_SHPPG_TRP_TYP)
	 */
	String retailShoppingTripTypeCode;

	/**
	 * TransactionEntryModeCode A code that designates the privlege mode or
	 * level that the individual creating this transaction used. The purpose is
	 * to track illicit uses of manager and maintenance modes that might be used
	 * to circumvent normal operational and financial controls Sample values:
	 * NORMAL - lowest privilege level for cashiers MANAGER - intermeidate
	 * privilege level fo rmanagers MAINTENANCE - highest leve privilege for
	 * auditors, tax authorities and authorized POS maintenance indiviuals.
	 * CD_TRN_ENR_MOD Code varchar(20)
	 */
	String transactionEntryModeCode;

	/**
	 * CustomerIDTypeCode Designates the type of identification documentation
	 * provided by a customer for this retail transaction. This is used to tell
	 * how a customer verifies their identity to a retailer to establish an
	 * association between the retail transaction and a customer account. This
	 * provides a place to indicate that a customer identification action was
	 * taken independent of a tender or other mechanism to associate a customer
	 * with an account (and make them an identified customer). This helps for
	 * loyalty customers that pay in cash or other tender that has no retailer
	 * based account associated with it. The following codes are examples:
	 * ANONYMOUS - no ID, anonymous customer this means that there is no cust
	 * account relationship recorded for this retail transaction. LOYALTY_CARD -
	 * Loyalty membership card PHONE_NBR - phone number (to look up acct when a
	 * customer forgot their card CUST_CHG - Customer's retailer-provided charge
	 * card (not a bank card) CUST_LAYAWAY - Layaway receipt (or other evidence
	 * of a layaway acct) RENTAL - rental receipt or other evidence of a rental
	 * account CD_CT_ID_TYP Code varchar(20)
	 */
	String customerIDTypeCode;

	/**
	 * ISOCurrencyCode (FK) Currency code designated by ISO to identify national
	 * currency CD_CNY_ISO_4217 ISO_4217_CurrencyCode_char(3) char(3)
	 * ISO4217-CurrencyType (LU_CNY_ISO_4217)
	 */
	String ISOCurrenyCode;

	Set<RetailTransactionLineItem> retailTransactionLineItems;

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getRetailTransactionTypeCode() {
		return retailTransactionTypeCode;
	}

	public void setRetailTransactionTypeCode(String retailTransactionTypeCode) {
		this.retailTransactionTypeCode = retailTransactionTypeCode;
	}

	public double getRingElapsedTime() {
		return ringElapsedTime;
	}

	public void setRingElapsedTime(double ringElapsedTime) {
		this.ringElapsedTime = ringElapsedTime;
	}

	public int getTillID() {
		return tillID;
	}

	public void setTillID(int tillID) {
		this.tillID = tillID;
	}

	public double getTenderElapsedTime() {
		return tenderElapsedTime;
	}

	public void setTenderElapsedTime(double tenderElapsedTime) {
		this.tenderElapsedTime = tenderElapsedTime;
	}

	public double getIdleElapsedTime() {
		return idleElapsedTime;
	}

	public void setIdleElapsedTime(double idleElapsedTime) {
		this.idleElapsedTime = idleElapsedTime;
	}

	public double getLockElapsedTime() {
		return lockElapsedTime;
	}

	public void setLockElapsedTime(double lockElapsedTime) {
		this.lockElapsedTime = lockElapsedTime;
	}

	public double getLineItemsScannedCount() {
		return lineItemsScannedCount;
	}

	public void setLineItemsScannedCount(double lineItemsScannedCount) {
		this.lineItemsScannedCount = lineItemsScannedCount;
	}

	public double getLineItemsKeyedCount() {
		return lineItemsKeyedCount;
	}

	public void setLineItemsKeyedCount(double lineItemsKeyedCount) {
		this.lineItemsKeyedCount = lineItemsKeyedCount;
	}

	public double getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(double unitCount) {
		this.unitCount = unitCount;
	}

	public double getLineItemsScannedPercent() {
		return lineItemsScannedPercent;
	}

	public void setLineItemsScannedPercent(double lineItemsScannedPercent) {
		this.lineItemsScannedPercent = lineItemsScannedPercent;
	}

	public double getLineItemsKeyedPercent() {
		return lineItemsKeyedPercent;
	}

	public void setLineItemsKeyedPercent(double lineItemsKeyedPercent) {
		this.lineItemsKeyedPercent = lineItemsKeyedPercent;
	}

	public double getKeyDepartmentCount() {
		return keyDepartmentCount;
	}

	public void setKeyDepartmentCount(double keyDepartmentCount) {
		this.keyDepartmentCount = keyDepartmentCount;
	}

	public double getKeyDepartmentPercent() {
		return keyDepartmentPercent;
	}

	public void setKeyDepartmentPercent(double keyDepartmentPercent) {
		this.keyDepartmentPercent = keyDepartmentPercent;
	}

	public Date getReceiptDateTime() {
		return receiptDateTime;
	}

	public void setReceiptDateTime(Date receiptDateTime) {
		this.receiptDateTime = receiptDateTime;
	}

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getCustomerIDEntryMethodCode() {
		return customerIDEntryMethodCode;
	}

	public void setCustomerIDEntryMethodCode(String customerIDEntryMethodCode) {
		this.customerIDEntryMethodCode = customerIDEntryMethodCode;
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public String getRetailShoppingTripTypeCode() {
		return retailShoppingTripTypeCode;
	}

	public void setRetailShoppingTripTypeCode(String retailShoppingTripTypeCode) {
		this.retailShoppingTripTypeCode = retailShoppingTripTypeCode;
	}

	public String getTransactionEntryModeCode() {
		return transactionEntryModeCode;
	}

	public void setTransactionEntryModeCode(String transactionEntryModeCode) {
		this.transactionEntryModeCode = transactionEntryModeCode;
	}

	public String getCustomerIDTypeCode() {
		return customerIDTypeCode;
	}

	public void setCustomerIDTypeCode(String customerIDTypeCode) {
		this.customerIDTypeCode = customerIDTypeCode;
	}

	public String getISOCurrenyCode() {
		return ISOCurrenyCode;
	}

	public void setISOCurrenyCode(String iSOCurrenyCode) {
		ISOCurrenyCode = iSOCurrenyCode;
	}

	public Set<RetailTransactionLineItem> getRetailTransactionLineItems() {
		return retailTransactionLineItems;
	}

	public void setRetailTransactionLineItems(
			Set<RetailTransactionLineItem> retailTransactionLineItems) {
		this.retailTransactionLineItems = retailTransactionLineItems;
	}

}
