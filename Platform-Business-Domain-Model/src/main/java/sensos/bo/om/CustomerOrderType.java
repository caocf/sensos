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
 * A retailer defined type code for an Order, that effect the way the order is
 * treated by the order management systems. Possible values include: WebOrder,
 * Layaway, BackOrder, etc.
 * 
 * @author sensos
 *
 */
public class CustomerOrderType {

	/**
	 * CustomerOrderTypeCode (PK) A unique retailer assigned code denoting a
	 * type of CustomerOrder Eg: Layaway, Order for Delivery, Order for Pickup,
	 * etc... CD_TYP_CTORD Code2 char(2)
	 **/
	String customerOrderTypeCode;

	/**
	 * CustomerOrderTypeName The name of the CustomerOrderType NM_TYP_CTORD Name
	 * varchar(40)
	 */
	String customerOrderTypeName;

	/**
	 * CustomerOrderTermsAndConditionsCode (FK) A unique retailer assigned code
	 * denoting a set of Terms and Conditions for a Customer Order.
	 * CD_CTORD_TRMCD Code2 char(2) CustomerOrderTermsAndConditions
	 * (CO_CTORD_TRMCD)
	 */
	String customerOrderTermsAndConditionsCode;

	public String getCustomerOrderTypeCode() {
		return customerOrderTypeCode;
	}

	public void setCustomerOrderTypeCode(String customerOrderTypeCode) {
		this.customerOrderTypeCode = customerOrderTypeCode;
	}

	public String getCustomerOrderTypeName() {
		return customerOrderTypeName;
	}

	public void setCustomerOrderTypeName(String customerOrderTypeName) {
		this.customerOrderTypeName = customerOrderTypeName;
	}

	public String getCustomerOrderTermsAndConditionsCode() {
		return customerOrderTermsAndConditionsCode;
	}

	public void setCustomerOrderTermsAndConditionsCode(
			String customerOrderTermsAndConditionsCode) {
		this.customerOrderTermsAndConditionsCode = customerOrderTermsAndConditionsCode;
	}

}
