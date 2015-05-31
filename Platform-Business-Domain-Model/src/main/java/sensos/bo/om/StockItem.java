/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensos.bo.om;

import java.sql.Date;

/**
 * A unit of merchandise that may be sold to a customer or used by the RETAIL
 * STORE.
 * 
 * @link 
 *       https://nrf.com/sites/default/files/ODM7_website_2014-03_21/F3C50F42-FA3B
 *       -49F0-8C22-8311B079D1DB.html
 * @author sensos
 */
public class StockItem extends Item {

	/**
	 * The code used to specify the units in which a value is being expressed,
	 * or manner in which a measurement has been taken. This code relates to the
	 * UCC data element 355.
	 */
	String unitOfMeasureCode;

	/**
	 * (FK) A unique retailer assigned idendtifier for the grouping of similar
	 * colors used to classify the sizes of merchandise for analysis.
	 */
	long sizeFamilyID;

	/**
	 * A retailer assigned code that indicates whether the StockItem is sold by
	 * weight or as an unit.
	 */
	String saleWeightOrUnitCountCode;

	/**
	 * (FK) A code to designate the size of the ITEM. It is proposed to use the
	 * 5 apparel and miscellaneous coding structure as specified by the NRMA to
	 * facilitate data interchange between vendors and retailers. This code
	 * permits the use of both standard and proprietary coding format.
	 */
	String sizeCode;

	/**
	 * (FK) A code that uniquely identifies the specific appearance type or
	 * variety in which an APPAREL ITEM is available.
	 */
	String styleCode;

	/**
	 * A retailer assigned code that defines where and how a customer may pickup
	 * this item. For examples a refrigerator may have to be picked up at the
	 * shipping dock or at the retailers warehouse.
	 */
	String customerPickupTypeCode;

	/**
	 * The number of units of measure per selling unit. Used as the divisor when
	 * calculating the STOCK ITEM's unit retail price, eg $1.67 per pound or
	 * $2.59 for 32 floz.
	 */
	double unitPriceFactor;

	/**
	 * (FK) A code to identify the color. The assumption is that the retailer
	 * will adopt the standards as designated by the NRF, created to facilitate
	 * the exchange of data between vendors and retailers.
	 */
	String colorCode;

	/**
	 * A retailer assigned code that defines the temperature, relative humidity,
	 * lighting and other physical or climatic environmental requirements for
	 * storing and displaying the item.
	 */
	String environmentTypeCode;

	/**
	 * A retailer assigned code that defines the security environment and
	 * procedures required for receiving, displaying and selling the item. This
	 * is for high-priced merchandise like jewelry, certain prescription drugs,
	 * ordinance, fireworks, etc.
	 */
	String securityRequiredTypeCode;

	/**
	 * A retailer assigned code to indicate the StockItem type, ie ShelfItem,
	 * ApparelItem, SerializedItem, etc.
	 */
	String stockItemTypeCode;

	/**
	 * A retailer assigned code that defines the relevant hazardous material
	 * handling properties of the item. This code is provided for oil products,
	 * petsicides, swimming pool suppliers, fertilizers (especially bomb grade),
	 * etc.
	 */
	String sazardousMaterialTypeCode;

	/**
	 * A retailer assigned code that defines the inventory accounting method to
	 * be used for the item. Examples of methods include the retail method, cost
	 * method, etc.
	 */
	String inventoryAccountingMethodCode;

	/**
	 * The base cost per sell unit derived from the last receipt of this item.
	 * The base cost excludes allowances, discounts, charges and other amounts
	 * that may change the item cost.
	 */
	double sellUnitLastReceivedBaseCostAmount;

	/**
	 * The net cost per sell unit derived from the last receipt of this item.
	 * The base cost includes allowances, discounts, charges and other amounts
	 * that may change the item cost. Where there are no allowances, etc. the net
	 * cost will equal the base cost for an item.
	 */
	double sellUnitLastReceivedNetCostAmount;

	/**
	 * The cost of the item plus the insurance, drayage, cartage, delivery,
	 * insurance, customs duties, etc. which add up to the full delivered cost
	 * of an imported item to the store.
	 */
	double sellUnitLandedCostAmount;

	/** The date the last received costs (net and base) were established. */
	Date sellUnitLastReceivedCostsEstablishedDate;

	/**
	 * The date when this STOCK ITEM becomes available for sale. For example,
	 * certain books have specific publication dates, music entertainment
	 * release dates
	 */
	Date availableForSaleDate;

	/**
	 * A flag to denote if the STOCK ITEM could loose weight from the time of
	 * order until the time of receipt.
	 */
	byte shrinkFlag;

	/**
	 * A flag used to indicate if the STOCK ITEM may gain weight or swell from
	 * time of order to time of receipt.
	 */
	byte swellFlag;

	/**
	 * The code used to specify the units in which a value is being expressed,
	 * or manner in which a measurement has been taken. This code relates to the
	 * UCC data element 355.
	 */
	String retailPackageSize;

	public String getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}

	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}

	public long getSizeFamilyID() {
		return sizeFamilyID;
	}

	public void setSizeFamilyID(long sizeFamilyID) {
		this.sizeFamilyID = sizeFamilyID;
	}

	public String getSaleWeightOrUnitCountCode() {
		return saleWeightOrUnitCountCode;
	}

	public void setSaleWeightOrUnitCountCode(String saleWeightOrUnitCountCode) {
		this.saleWeightOrUnitCountCode = saleWeightOrUnitCountCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}

	public String getCustomerPickupTypeCode() {
		return customerPickupTypeCode;
	}

	public void setCustomerPickupTypeCode(String customerPickupTypeCode) {
		this.customerPickupTypeCode = customerPickupTypeCode;
	}

	public double getUnitPriceFactor() {
		return unitPriceFactor;
	}

	public void setUnitPriceFactor(double unitPriceFactor) {
		this.unitPriceFactor = unitPriceFactor;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getEnvironmentTypeCode() {
		return environmentTypeCode;
	}

	public void setEnvironmentTypeCode(String environmentTypeCode) {
		this.environmentTypeCode = environmentTypeCode;
	}

	public String getSecurityRequiredTypeCode() {
		return securityRequiredTypeCode;
	}

	public void setSecurityRequiredTypeCode(String securityRequiredTypeCode) {
		this.securityRequiredTypeCode = securityRequiredTypeCode;
	}

	public String getStockItemTypeCode() {
		return stockItemTypeCode;
	}

	public void setStockItemTypeCode(String stockItemTypeCode) {
		this.stockItemTypeCode = stockItemTypeCode;
	}

	public String getSazardousMaterialTypeCode() {
		return sazardousMaterialTypeCode;
	}

	public void setSazardousMaterialTypeCode(String sazardousMaterialTypeCode) {
		this.sazardousMaterialTypeCode = sazardousMaterialTypeCode;
	}

	public String getInventoryAccountingMethodCode() {
		return inventoryAccountingMethodCode;
	}

	public void setInventoryAccountingMethodCode(
			String inventoryAccountingMethodCode) {
		this.inventoryAccountingMethodCode = inventoryAccountingMethodCode;
	}

	public double getSellUnitLastReceivedBaseCostAmount() {
		return sellUnitLastReceivedBaseCostAmount;
	}

	public void setSellUnitLastReceivedBaseCostAmount(
			double sellUnitLastReceivedBaseCostAmount) {
		this.sellUnitLastReceivedBaseCostAmount = sellUnitLastReceivedBaseCostAmount;
	}

	public double getSellUnitLastReceivedNetCostAmount() {
		return sellUnitLastReceivedNetCostAmount;
	}

	public void setSellUnitLastReceivedNetCostAmount(
			double sellUnitLastReceivedNetCostAmount) {
		this.sellUnitLastReceivedNetCostAmount = sellUnitLastReceivedNetCostAmount;
	}

	public double getSellUnitLandedCostAmount() {
		return sellUnitLandedCostAmount;
	}

	public void setSellUnitLandedCostAmount(double sellUnitLandedCostAmount) {
		this.sellUnitLandedCostAmount = sellUnitLandedCostAmount;
	}

	public Date getSellUnitLastReceivedCostsEstablishedDate() {
		return sellUnitLastReceivedCostsEstablishedDate;
	}

	public void setSellUnitLastReceivedCostsEstablishedDate(
			Date sellUnitLastReceivedCostsEstablishedDate) {
		this.sellUnitLastReceivedCostsEstablishedDate = sellUnitLastReceivedCostsEstablishedDate;
	}

	public Date getAvailableForSaleDate() {
		return availableForSaleDate;
	}

	public void setAvailableForSaleDate(Date availableForSaleDate) {
		this.availableForSaleDate = availableForSaleDate;
	}

	public byte getShrinkFlag() {
		return shrinkFlag;
	}

	public void setShrinkFlag(byte shrinkFlag) {
		this.shrinkFlag = shrinkFlag;
	}

	public byte getSwellFlag() {
		return swellFlag;
	}

	public void setSwellFlag(byte swellFlag) {
		this.swellFlag = swellFlag;
	}

	public String getRetailPackageSize() {
		return retailPackageSize;
	}

	public void setRetailPackageSize(String retailPackageSize) {
		this.retailPackageSize = retailPackageSize;
	}

}
