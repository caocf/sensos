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
import java.util.Set;

/**
 * A document that describes a request by a Customer to purchase merchandise
 * and/or service items from a retailer on agreed terms and conditions (price,
 * delivery, payments schedule, cancellation policy, etc.)
 * 
 * @author sensos
 * @link 
 *       https://nrf.com/sites/default/files/ODM7_website_2014-03_21/AD06BE97-12C6
 *       -4F7E-B8D7-6CFE99B8E4B7.html
 *
 */
public class CustomerOrder {

	/** (PK) A unique system assigned identifier for the CustomerOrder. */
	long customerOrderID;

	/**
	 * (FK) A unique retailer assigned code denoting a type of CustomerOrder
	 * Examples include: LAYAWAY ORDER FOR DELIVERY ORDER FOR PICKUP Eg:
	 * Layaway, Order for Delivery, Order for Pickup, etc... CD_TYP_CTORD Code2
	 * char(2) CustomerOrderType (CO_TYP_CTORD)
	 */
	String customerOrderTypeCode;

	/**
	 * (FK) The unique identifier of the Currency. ID_CNY Identity integer
	 * Currency (CO_CNY)
	 */
	String currencyID;

	/**
	 * The date the merchandise being ordered is expected to be available for
	 * delivery to the Customer or pickup by the Customer. DC_AVLB_EST
	 * DateCalendar date
	 */
	Date estimatedAvailabilityDate;

	/**
	 * The date all the merchandise being ordered was made available for
	 * delivery to the Customer or pickup by the Customer. Where the merchandise
	 * becomes available over a period of time - this field holds the date the
	 * last ordered product was available for delivery or pickup. DC_AVLB_ACT
	 * DateCalendar date
	 */
	Date actualAvailabilityDate;

	/**
	 * (FK) A unique system assigned identifier for the Customer. ID_CT Identity
	 * integer Customer (PA_CT)
	 */
	long customerID;

	/**
	 * An separate identifier (from that of a customer order id) for a special
	 * order ID_ORD_SPL IdentityAlphaNumeric varchar(20)
	 */
	String specialOrderID;
	/**
	 * (FK) Token Identifier for a channel instance ID_CHNL Identity integer
	 * Channel (CO_CHNL)
	 */
	int channelID;

	/**
	 * (FK) Currency code designated by ISO to identify national currency
	 * CD_CNY_ISO_4217 ISO_4217_CurrencyCode_char(3) char(3)
	 * ISO4217-CurrencyType (LU_CNY_ISO_4217)
	 */
	String isoCurrencyCode;

	Set<CustomerOrderLineItem> customerOrderLineItems;

	public CustomerOrder(Customer customer) {
		if (customer == null)
			throw new IllegalArgumentException(
					"Incoming customer cannot be null");
		if (customer.getCustomerId() == null)
			throw new IllegalArgumentException(
					"Incoming customerId cannot be null");
	}

	public CustomerOrder() {
		// TODO Auto-generated constructor stub
	}

	public long getCustomerOrderID() {
		return customerOrderID;
	}

	public void setCustomerOrderID(long customerOrderID) {
		this.customerOrderID = customerOrderID;
	}

	public String getCustomerOrderTypeCode() {
		return customerOrderTypeCode;
	}

	public void setCustomerOrderTypeCode(String customerOrderTypeCode) {
		this.customerOrderTypeCode = customerOrderTypeCode;
	}

	public String getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
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

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public String getSpecialOrderID() {
		return specialOrderID;
	}

	public void setSpecialOrderID(String specialOrderID) {
		this.specialOrderID = specialOrderID;
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public String getIsoCurrencyCode() {
		return isoCurrencyCode;
	}

	public void setIsoCurrencyCode(String isoCurrencyCode) {
		this.isoCurrencyCode = isoCurrencyCode;
	}

	public Set<CustomerOrderLineItem> getCustomerOrderLineItems() {
		return customerOrderLineItems;
	}

	public void setCustomerOrderLineItems(
			Set<CustomerOrderLineItem> customerOrderLineItems) {
		this.customerOrderLineItems = customerOrderLineItems;
	}

	public void addCustomerOrderLineItem(CustomerOrderLineItem col) {
		this.customerOrderLineItems.add(col);
	}

	public int getCustomerOrderLineItemCount() {
		if (customerOrderLineItems == null)
			return 0;
		return customerOrderLineItems.size();
	}

}
