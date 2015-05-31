/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensos.bo.om;

/**
 *
 * The lowest level of merchandise for which inventory and sales records are
 * retained within the retail enterprise. It is analogous to the SKU ( Stock
 * Keeping Unit).
 * 
 * @link 
 *       https://nrf.com/sites/default/files/ODM7_website_2014-03_21/79947F5C-6BC3
 *       -4123-9C0F-595322B4E104.html
 * @author sensos
 */
public class Item {

	/** (PK) A unique system assigned identifier for the retailers SKU. */
	String itemId;

	/** (FK) Unique identifier for this price point. */
	long pricelineId;

	/** (FK) A unique system assigned identifier for the ItemSellingPrices. */
	long itemSellingPricesID;

	/**
	 * (FK) Unique system assigned identifier for a group or class of
	 * merchandise.
	 */
	long merchandiseHierarchyGroupID;

	/** (FK) A unique system assigned identifier for the ItemSellingPrices. */
	long itemSellingRuleID;

	/** (FK) A unique system-assigned identifier for the POSDepartment. */
	long POSDepartmentID;

	/** A flag to indicate whether this Item can be discounted. */
	long discoutFlag;

	/**
	 * A flag to denote whether this ITEM was validated (scanned) during
	 * verification of the ITEM table.
	 */
	long priceAuditFlag;

	/**
	 * (FK) A unique name to denote a class of ITEMs as a product. The brand can
	 * include private label ITEMs.
	 */
	long brandName;

	/**
	 * A flag to indicate that the RETAIL STORE is authorized to stock this
	 * particular ITEM
	 */
	long authorizedForSaleFlag;

	/**
	 * This code defines how this item may be used within a store. Usage is a
	 * function of how an item may be consumed or disposed of by the store.
	 */
	String usageCode;

	/** The name by which the Item is known */
	String name;

	/** The textual description of the Item and its characteristics. */
	String description;

	/**
	 * This code indicates which subtype this item is. Examples are Stock Item,
	 * Aggregate Item, Service Item, Prepared Item, Group Select Item, etc.
	 */
	String typeCode;

	/**
	 * This code signifies this ITEM is provided as a kit which has to made up
	 * either by the customer or by the store at an additional cost.
	 */
	String kitSetCode;

	/** A longer textual description of the Item and its characteristics. */
	String longDescription;

	/**
	 * An ITEM for which there is a substitute available for sale within the
	 * RETAIL STORE.
	 */
	long substituteIdentifiedFlag;

	/**
	 * A code to signify that this ITEM is ordered as part of a collection of
	 * ITEMs
	 */
	String orderCollectionCode;

	/**
	 * A code to denote the tax exemption status from sales and use tax. The
	 * codes refer to the UCC code, Tax Exempt Code, defined in data element
	 * 441.
	 */
	String taxExemptCode;

	/**
	 * An indicator used to identify if the SERIALIZED ITEM requires operator
	 * validation after payment has been made and prior to customer pickup.
	 */
	long serializedUnitValidationFlag;

	/** (FK) A sequence number that identifies a subbrand within a brand. */
	int subBrand;

	/**
	 * (FK) A unique name to denote a class of ITEMs as a product. The brand can
	 * include private label ITEMs.
	 */
	String subBranName;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public long getPricelineId() {
		return pricelineId;
	}

	public void setPricelineId(long pricelineId) {
		this.pricelineId = pricelineId;
	}

	public long getItemSellingPricesID() {
		return itemSellingPricesID;
	}

	public void setItemSellingPricesID(long itemSellingPricesID) {
		this.itemSellingPricesID = itemSellingPricesID;
	}

	public long getMerchandiseHierarchyGroupID() {
		return merchandiseHierarchyGroupID;
	}

	public void setMerchandiseHierarchyGroupID(long merchandiseHierarchyGroupID) {
		this.merchandiseHierarchyGroupID = merchandiseHierarchyGroupID;
	}

	public long getItemSellingRuleID() {
		return itemSellingRuleID;
	}

	public void setItemSellingRuleID(long itemSellingRuleID) {
		this.itemSellingRuleID = itemSellingRuleID;
	}

	public long getPOSDepartmentID() {
		return POSDepartmentID;
	}

	public void setPOSDepartmentID(long pOSDepartmentID) {
		POSDepartmentID = pOSDepartmentID;
	}

	public long getDiscoutFlag() {
		return discoutFlag;
	}

	public void setDiscoutFlag(long discoutFlag) {
		this.discoutFlag = discoutFlag;
	}

	public long getPriceAuditFlag() {
		return priceAuditFlag;
	}

	public void setPriceAuditFlag(long priceAuditFlag) {
		this.priceAuditFlag = priceAuditFlag;
	}

	public long getBrandName() {
		return brandName;
	}

	public void setBrandName(long brandName) {
		this.brandName = brandName;
	}

	public long getAuthorizedForSaleFlag() {
		return authorizedForSaleFlag;
	}

	public void setAuthorizedForSaleFlag(long authorizedForSaleFlag) {
		this.authorizedForSaleFlag = authorizedForSaleFlag;
	}

	public String getUsageCode() {
		return usageCode;
	}

	public void setUsageCode(String usageCode) {
		this.usageCode = usageCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getKitSetCode() {
		return kitSetCode;
	}

	public void setKitSetCode(String kitSetCode) {
		this.kitSetCode = kitSetCode;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public long getSubstituteIdentifiedFlag() {
		return substituteIdentifiedFlag;
	}

	public void setSubstituteIdentifiedFlag(long substituteIdentifiedFlag) {
		this.substituteIdentifiedFlag = substituteIdentifiedFlag;
	}

	public String getOrderCollectionCode() {
		return orderCollectionCode;
	}

	public void setOrderCollectionCode(String orderCollectionCode) {
		this.orderCollectionCode = orderCollectionCode;
	}

	public String getTaxExemptCode() {
		return taxExemptCode;
	}

	public void setTaxExemptCode(String taxExemptCode) {
		this.taxExemptCode = taxExemptCode;
	}

	public long getSerializedUnitValidationFlag() {
		return serializedUnitValidationFlag;
	}

	public void setSerializedUnitValidationFlag(
			long serializedUnitValidationFlag) {
		this.serializedUnitValidationFlag = serializedUnitValidationFlag;
	}

	public int getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(int subBrand) {
		this.subBrand = subBrand;
	}

	public String getSubBranName() {
		return subBranName;
	}

	public void setSubBranName(String subBranName) {
		this.subBranName = subBranName;
	}

}
