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

/**
 * A line item component of a RetailTransaction that records the exchange in
 * ownership of a merchandise item (i.e. a sale or return) or the sale or refund
 * related to a service. The Sale or refund related to a service captures an
 * item and action taken that reflects an event of interest to the retail
 * business but DOES NOT RESULT in the exchange in ownership of a merchandise
 * item.
 * 
 * @link 
 *       https://nrf.com/sites/default/files/ODM7_website_2014-03_21/261B6A7A-2575
 *       -4604-A7B3-B20238087360.html
 * @author sensos
 *
 */
public class SaleReturnLineItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3419818084818826431L;

	/**
	 * TransactionID (PK)(FK) A universally unique identifier (UUID) for the
	 * Transaction. This may be assembled from alternate key members. ID_TRN
	 * IdentityUUID char(32) RetailTransactionLineItem (TR_LTM_RTL_TRN)
	 */
	String transactionID;

	/**
	 * SellingLocationID (FK) A unique system assigned identifier for the
	 * SellingLocation. ID_LCN Identity integer SellingLocation (LO_LCN_SL)
	 */
	int sellingLocationID;

	/**
	 * BusinessUnitGroupID (FK) A unique system assigned identifier for a group
	 * of BusinessUnits. ID_BSNGP Identity integer POSIdentity (ID_PS)
	 */
	int businessUnitGroupID;

	/**
	 * RetailTransactionLineItemSequenceNumber (PK)(FK) The sequence number of
	 * line item within the context of this RetailTransaction. IC_LN_ITM
	 * LineNumber smallint RetailTransactionLineItem (TR_LTM_RTL_TRN)
	 */
	int retailTransactionLineItemSequenceNumber;

	/**
	 * ItemLookupMethodCode A mnemonic code denoting which code was used to
	 * enter the Item into the RetailTransaction. e.g. POSIdentity,
	 * POSIdentiyAndQualifier, ItemID, POSDepartmentID, CD_MTH_ITM_LKP Code4
	 * char(4)
	 */
	String itemLookupMethodCode;

	/**
	 * POSItemID (FK) The barcode, point of sale scan code or other keyed
	 * identifying number used at POS and the internal stock keping ItemID for
	 * the item. Will generally be filled with the GTIN (UPC, EAN etc) for an
	 * item -- but it is not mandatory -- A retailer may develop and maintain its
	 * own set of POS identifiers. ID_ITM_PS IdentityGTIN varchar(14) POSIdentity
	 * (ID_PS)
	 */
	String POSItemID;

	/**
	 * POSItemIDQualifier (FK) A secondary qualifier on the POSItemID which
	 * denotes a separate item. Eg: When single bottles & six-packs of the same
	 * beverage both have the same UPC or other barcode, the POSItemIDQualifier
	 * holds denotes the two different items. ID_ITM_PS_QFR Identity integer
	 * POSIdentity (ID_PS)
	 */
	int POSItemIDQualifier;

	/**
	 * ItemID (FK) A unique system assigned identifier for the retailer's SKU.
	 * ID_ITM IdentityUUID char(32) Item (AS_ITM)
	 */
	String itemID;

	/**
	 * POSDepartmentID (FK) A unique system-assigned identifier for the
	 * POSDepartment ID_DPT_PS Identity integer POSDepartment (ID_DPT_PS)
	 */
	int POSDepartmentID;

	/**
	 * UnitOfMeasureCode (FK) The code used to specify the units in which a
	 * value is being expressed, or manner in which a measurement has been
	 * taken. This code relates to the UCC data element 355. CD_UOM Code
	 * varchar(20) UnitOfMeasure (CO_UOM)
	 */
	String unitOfMeasureCode;

	/**
	 * ItemType Meta-Data denoting the kind of item being sold (or returned) in
	 * the line item e.g. StockItem, ServiceItem, AggregateItem etc.. TY_ITM
	 * Code4 char(4)
	 */
	String itemType;

	/**
	 * SubItemType Meta-Data denoting the kind of StockItem or ServiceItem being
	 * sold (or returned) in the line item e.g. FuelItem, BulkItem, ApparelItem,
	 * RentalItem, MiscellaneousFee, etc... TY_ITM_SUB Code4 char(4)
	 */
	String subItemType;

	/**
	 * RegularUnitPrice The regular or lookup per-unit price for the item before
	 * any discounts have been applied. MO_PRC_REG MoneyShortRetail decimal(7,2)
	 */
	double regularUnitPrice;

	/**
	 * Quantity The number of retail selling units sold to or returned by a
	 * customer. For services the number of units (e.g. hours or job) sold or in
	 * the case of refunds, reduced to zero revenue. QU_ITM_LM_RTN_SLS Quantity
	 * DECIMAL(9,2)
	 */
	double quantity;

	/**
	 * RegularUnitPriceQuantity The number of sellable units applicable to the
	 * regular or lookup-up price of the Item at the time of the Transaction.
	 * e.g. 3 for 99c This attribute should default to a value of 1 which means
	 * that a unit retail price applies to one sellable unit. UN_UPRQY_REG
	 * Quantity DECIMAL(9,2)
	 */
	double regularUnitPriceQuantity;

	/**
	 * ActualUnitPrice The actual per-unit price paid by the customer for this
	 * particular sale. It is obtained by applying applicable price derivation
	 * rules to the regular unit price. MO_PRC_ACT MoneyShortRetail decimal(7,2)
	 */
	double actualUnitPrice;

	/**
	 * ExtendedAmount The product of multiplying Quantity by the retail selling
	 * unit price derived from price lookup (and any applicable price derivation
	 * rules) (i.e.,ActualUnitPrice). This retail sale unit price excludes sales
	 * and/or value added tax. MO_EXTND Money decimal(16,5)
	 **/
	double extendedAmount;

	/**
	 * ActualUnitPriceQuantity The number of units applicable to the actual
	 * per-unit price paid by the customer for this particular sale. e.g. 3 for
	 * 87c UN_UPRQY_ACT Quantity DECIMAL(9,2)
	 **/
	double actualUnitPriceQuantity;

	/**
	 * BultkUnitCount The number of units sold, when selling bulk merchandise.
	 * Eg: A sale of 20 Gallons of Gas: Quantity=20, Units=1, UnitOfMeasure=Ga
	 * Eg: A sale of 3 cans of Beans: Quantity=3, Units=3, UnitOfMeasure=Ea QU_UN
	 * Quantity DECIMAL(9,2)
	 */
	double bulkUnitCount;

	/**
	 * ItemIDEntryMethodCode A code that describes how this line item's item
	 * identification is being entered (e.g. it is scanned, keyed SKU, keyed
	 * department, etc.) LU_MTH_ID_ENR Code4 char(4)
	 */
	String itemIDEntryMethodCode;

	/**
	 * ActionCode A code denoting how the item is being treated in the line
	 * item. Sample values include: SL = Sale RT = Return LU_ACTN_CD Code2
	 * char(2)
	 */
	String actionCode;

	/**
	 * UnitDiscountAmount The monetary total per-unit of all Discounts and
	 * RetailPriceModifiers that were applied to this Item MO_DSC_UN
	 * MoneyShortRetail decimal(7,2)
	 */
	double unitDiscountAmount;

	/**
	 * ExtendedDiscountAmount The monetary total of all Discounts and
	 * RetailPriceModifiers that were applied to this Item MO_DSC_UN_EXT
	 * MoneyShortRetail decimal(7,2)
	 */
	double extendedDiscountAmount;

	/**
	 * SellUnitRetailPriceEntryMethodCode A code that describes how this line
	 * item's retail selling unit price is being entered (e.g. it is manually
	 * keyed, obtained from price lookup, etc.) LU_ENR_RT_PRC Code4 char(4)
	 */
	String sellUnitRetailPriceEntryMethodCode;

	/**
	 * ReasonCode A unique retailer defined reason code for an action that is
	 * taken (or not taken) at a Workstation. e.g. Return reason codes - Past
	 * Use By Date, Defective Merchandise, etc. POSNoSale reason Codes - Customer
	 * Change Query CD_RSN Code varchar(20)
	 */
	String reasonCode;

	/**
	 * SellUnitRetailPriceDerivationMethodCode A code that documents how the
	 * selling unit retail prices were derived for this line item.
	 * LU_PRC_RT_DRVN Code4 char(4)
	 */
	String sellUnitRetailPriceDerivationMethodCode;

	/**
	 * FulfillmentAcknowledgementLineItemSequenceNumber (FK) The sequence number
	 * of the FulfillmentAcknowledgment LineItem that records the fulfillment of
	 * this sale to the Customer. IC_LTM_FLF_ACK LineNumber smallint
	 * FulfillmentAcknowledgmentLineItem (TR_LTM_FLF_ACK)
	 */
	int fulfillmentAcknowledgementLineItemSequenceNumber;

	/**
	 * UnitCostPrice The unit cost of the Item to the retail enterprise at the
	 * time of the Transaction. CP_UN MoneyShortCost decimal(16,5)
	 */
	double unitCostPrice;

	/**
	 * UnitCostPriceQuantity The number of units applicable to the cost of the
	 * Item to the retail enterprise at the time of the Transaction e.g. 3 for
	 * 99c Where this value is > 1, the effective UnitCostPrice per selling unit
	 * is the UnitCostPrice / UnitCostPriceQuantity. So a unit cost of $.45 with
	 * a designated UnitCostPriceQuantity of 3 has an effecive UnitCostPrice per
	 * selling unit of $.15. UN_UPRQY Quantity DECIMAL(9,2)
	 */
	double unitCostPriceQuantity;

	/**
	 * UnitListPrice The unit MSRP of the Item at the time of the Transaction
	 * RP_MSRP MoneyShortRetail decimal(7,2)
	 */
	double unitListPrice;

	/**
	 * UnitListPriceQuantity The number of units applicable to the MSRP of the
	 * Item at the time of the Transaction e.g. 3 for 99c UN_MSRP_UPRQY Quantity
	 * DECIMAL(9,2)
	 */
	double unitListPriceQuantity;

	/**
	 * InventoryValuePrice The value per unit of the Item (at retail) taken from
	 * the StockLedger at the time of the Transaction. CP_INV MoneyShortCost
	 * decimal(16,5)
	 */
	double inventoryValuePrice;

	/**
	 * InventoryValuePriceQuantity The number of units applicable to the value
	 * per unit of the Item taken from the StockLedger at the time of the
	 * Transaction. e.g. 3 for 99c UN_INV_UPRQY Quantity DECIMAL(9,2)
	 */
	double inventoryValuePriceQuantity;

	/**
	 * GiftReceiptFlag A flag denoting that an abbreviated GiftReceipt was
	 * issued for this LineItem. This may mean that return processing will be
	 * different if the item is returned. FL_GF_RCT Flag integer
	 */
	int giftReceiptFlag;

	/**
	 * ItemTraceableUnitID (FK) A universally unique identifier used to identify
	 * an instance of a distinguisable instance of a retail item. The item
	 * traceable unit ID is similar to a serial number in that it allows each
	 * selling unit to be explicitly identified and distinguished from other
	 * sellling units of the same Item. ID_ITM_UN_TRC IdentityUUID CHAR(32)
	 * ItemTraceableUnit (AS_ITM_TRCBL_UN)
	 */
	String itemTraceableUnitID;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getSellingLocationID() {
		return sellingLocationID;
	}

	public void setSellingLocationID(int sellingLocationID) {
		this.sellingLocationID = sellingLocationID;
	}

	public int getBusinessUnitGroupID() {
		return businessUnitGroupID;
	}

	public void setBusinessUnitGroupID(int businessUnitGroupID) {
		this.businessUnitGroupID = businessUnitGroupID;
	}

	public int getRetailTransactionLineItemSequenceNumber() {
		return retailTransactionLineItemSequenceNumber;
	}

	public void setRetailTransactionLineItemSequenceNumber(
			int retailTransactionLineItemSequenceNumber) {
		this.retailTransactionLineItemSequenceNumber = retailTransactionLineItemSequenceNumber;
	}

	public String getItemLookupMethodCode() {
		return itemLookupMethodCode;
	}

	public void setItemLookupMethodCode(String itemLookupMethodCode) {
		this.itemLookupMethodCode = itemLookupMethodCode;
	}

	public String getPOSItemID() {
		return POSItemID;
	}

	public void setPOSItemID(String pOSItemID) {
		POSItemID = pOSItemID;
	}

	public int getPOSItemIDQualifier() {
		return POSItemIDQualifier;
	}

	public void setPOSItemIDQualifier(int pOSItemIDQualifier) {
		POSItemIDQualifier = pOSItemIDQualifier;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public int getPOSDepartmentID() {
		return POSDepartmentID;
	}

	public void setPOSDepartmentID(int pOSDepartmentID) {
		POSDepartmentID = pOSDepartmentID;
	}

	public String getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}

	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSubItemType() {
		return subItemType;
	}

	public void setSubItemType(String subItemType) {
		this.subItemType = subItemType;
	}

	public double getRegularUnitPrice() {
		return regularUnitPrice;
	}

	public void setRegularUnitPrice(double regularUnitPrice) {
		this.regularUnitPrice = regularUnitPrice;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getRegularUnitPriceQuantity() {
		return regularUnitPriceQuantity;
	}

	public void setRegularUnitPriceQuantity(double regularUnitPriceQuantity) {
		this.regularUnitPriceQuantity = regularUnitPriceQuantity;
	}

	public double getActualUnitPrice() {
		return actualUnitPrice;
	}

	public void setActualUnitPrice(double actualUnitPrice) {
		this.actualUnitPrice = actualUnitPrice;
	}

	public double getExtendedAmount() {
		return extendedAmount;
	}

	public void setExtendedAmount(double extendedAmount) {
		this.extendedAmount = extendedAmount;
	}

	public double getActualUnitPriceQuantity() {
		return actualUnitPriceQuantity;
	}

	public void setActualUnitPriceQuantity(double actualUnitPriceQuantity) {
		this.actualUnitPriceQuantity = actualUnitPriceQuantity;
	}

	public double getBulkUnitCount() {
		return bulkUnitCount;
	}

	public void setBulkUnitCount(double bulkUnitCount) {
		this.bulkUnitCount = bulkUnitCount;
	}

	public String getItemIDEntryMethodCode() {
		return itemIDEntryMethodCode;
	}

	public void setItemIDEntryMethodCode(String itemIDEntryMethodCode) {
		this.itemIDEntryMethodCode = itemIDEntryMethodCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public double getUnitDiscountAmount() {
		return unitDiscountAmount;
	}

	public void setUnitDiscountAmount(double unitDiscountAmount) {
		this.unitDiscountAmount = unitDiscountAmount;
	}

	public double getExtendedDiscountAmount() {
		return extendedDiscountAmount;
	}

	public void setExtendedDiscountAmount(double extendedDiscountAmount) {
		this.extendedDiscountAmount = extendedDiscountAmount;
	}

	public String getSellUnitRetailPriceEntryMethodCode() {
		return sellUnitRetailPriceEntryMethodCode;
	}

	public void setSellUnitRetailPriceEntryMethodCode(
			String sellUnitRetailPriceEntryMethodCode) {
		this.sellUnitRetailPriceEntryMethodCode = sellUnitRetailPriceEntryMethodCode;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getSellUnitRetailPriceDerivationMethodCode() {
		return sellUnitRetailPriceDerivationMethodCode;
	}

	public void setSellUnitRetailPriceDerivationMethodCode(
			String sellUnitRetailPriceDerivationMethodCode) {
		this.sellUnitRetailPriceDerivationMethodCode = sellUnitRetailPriceDerivationMethodCode;
	}

	public int getFulfillmentAcknowledgementLineItemSequenceNumber() {
		return fulfillmentAcknowledgementLineItemSequenceNumber;
	}

	public void setFulfillmentAcknowledgementLineItemSequenceNumber(
			int fulfillmentAcknowledgementLineItemSequenceNumber) {
		this.fulfillmentAcknowledgementLineItemSequenceNumber = fulfillmentAcknowledgementLineItemSequenceNumber;
	}

	public double getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(double unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public double getUnitCostPriceQuantity() {
		return unitCostPriceQuantity;
	}

	public void setUnitCostPriceQuantity(double unitCostPriceQuantity) {
		this.unitCostPriceQuantity = unitCostPriceQuantity;
	}

	public double getUnitListPrice() {
		return unitListPrice;
	}

	public void setUnitListPrice(double unitListPrice) {
		this.unitListPrice = unitListPrice;
	}

	public double getUnitListPriceQuantity() {
		return unitListPriceQuantity;
	}

	public void setUnitListPriceQuantity(double unitListPriceQuantity) {
		this.unitListPriceQuantity = unitListPriceQuantity;
	}

	public double getInventoryValuePrice() {
		return inventoryValuePrice;
	}

	public void setInventoryValuePrice(double inventoryValuePrice) {
		this.inventoryValuePrice = inventoryValuePrice;
	}

	public double getInventoryValuePriceQuantity() {
		return inventoryValuePriceQuantity;
	}

	public void setInventoryValuePriceQuantity(
			double inventoryValuePriceQuantity) {
		this.inventoryValuePriceQuantity = inventoryValuePriceQuantity;
	}

	public int getGiftReceiptFlag() {
		return giftReceiptFlag;
	}

	public void setGiftReceiptFlag(int giftReceiptFlag) {
		this.giftReceiptFlag = giftReceiptFlag;
	}

	public String getItemTraceableUnitID() {
		return itemTraceableUnitID;
	}

	public void setItemTraceableUnitID(String itemTraceableUnitID) {
		this.itemTraceableUnitID = itemTraceableUnitID;
	}

}
