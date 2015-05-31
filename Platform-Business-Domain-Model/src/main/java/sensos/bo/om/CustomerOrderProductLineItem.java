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

import java.sql.Date;

/**
 *
 * Logical name : CustomerOrderProductLineItem Physical name: DO_CTOLTM_PDT A
 * line item component of a Customer placed order, recording the StockItem, and
 * quantity that is to be supplied.
 * 
 * @author sensos
 */
public class CustomerOrderProductLineItem extends CustomerOrderLineItem {

	/**
	 * ItemID (FK) A unique system assigned identifier for the retailer's SKU.
	 * ID_ITM IdentityUUID char(32) Item (AS_ITM)
	 */
	String itemID;

	/**
	 * Description The textual description of the Item being ordered and its
	 * characteristics. DE_ITM DescriptionShort varchar(255)
	 */
	String description;

	/**
	 * OrderedItemCount The number of Items being ordered. QU_ORD_CNT
	 * QuantityShortCount decimal(3,0)
	 **/
	double orderedItemCount;

	/**
	 * FulfilledItemCount The number of ordered items that have been made
	 * available for delivery to the Customer or pickup by the Customer.
	 * QU_FLF_CNT Quantity DECIMAL(9,2)
	 */
	double fulfilledItemCount;

	/**
	 * SaleUnitRetailPriceAmount The agreed unit retail selling price for the
	 * merchandise being ordered, RP_SLS_UN MoneyShortRetail decimal(7,2)
	 */
	double saleUnitRetailPriceAmount;

	/**
	 * TotalRetailPriceAmount The agreed retail selling price for all the
	 * merchandise being ordered on this line item. Would usually be calculated
	 * by multiplying SaleUnitRetailPriceAmount by OrderedCount - but this may
	 * differ. MO_PRC_TOT MoneyShortRetail decimal(7,2)
	 */
	double totalRetailPriceAmount;

	/**
	 * EstimatedAvailabilityDate The date the merchandise being ordered is
	 * expected to be available for delivery to the Customer or pickup by the
	 * Customer. DC_AVLB_EST DateCalendar date
	 */
	Date estimatedAvailabilityDate;

	/**
	 * ActualAvailabilityDate The date all the merchandise being ordered was
	 * made available for delivery to the Customer or pickup by the Customer.
	 * Where the merchandise becomes available over a period of time - this
	 * field holds the date the last ordered product was available for delivery
	 * or pickup. DC_AVLB_ACT DateCalendar date
	 */
	Date actualAvailabilityDate;

	public CustomerOrderProductLineItem(long customerOrderID,
			int customerOrderSequenceNumber) {
		super(customerOrderID, customerOrderSequenceNumber);
	}

	public CustomerOrderProductLineItem(long customerOrderID,
			int customerOrderSequenceNumber, String itemID) {
		super(customerOrderID, customerOrderSequenceNumber);
		this.itemID = itemID;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getOrderedItemCount() {
		return orderedItemCount;
	}

	public void setOrderedItemCount(double orderedItemCount) {
		this.orderedItemCount = orderedItemCount;
	}

	public double getFulfilledItemCount() {
		return fulfilledItemCount;
	}

	public void setFulfilledItemCount(double fulfilledItemCount) {
		this.fulfilledItemCount = fulfilledItemCount;
	}

	public double getSaleUnitRetailPriceAmount() {
		return saleUnitRetailPriceAmount;
	}

	public void setSaleUnitRetailPriceAmount(double saleUnitRetailPriceAmount) {
		this.saleUnitRetailPriceAmount = saleUnitRetailPriceAmount;
	}

	public double getTotalRetailPriceAmount() {
		return totalRetailPriceAmount;
	}

	public void setTotalRetailPriceAmount(double totalRetailPriceAmount) {
		this.totalRetailPriceAmount = totalRetailPriceAmount;
	}

	public Date getEstimatedAvailabilityDate() {
		return estimatedAvailabilityDate;
	}

	public void setEstimatedAvailabilityDate(Date estimatedAvailabilityDate) {
		this.estimatedAvailabilityDate = estimatedAvailabilityDate;
	}

	public Date getActualAvailabilityDate() {
		return actualAvailabilityDate;
	}

	public void setActualAvailabilityDate(Date actualAvailabilityDate) {
		this.actualAvailabilityDate = actualAvailabilityDate;
	}

}
