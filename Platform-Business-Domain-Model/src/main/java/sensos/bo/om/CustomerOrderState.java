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
 * A retailer defined state for an Order. Possible values include: Pending,
 * PartiallyDelivered, Complete, Cancelled etc.
 * 
 * @author sensos
 *
 */
public class CustomerOrderState {

	/**
	 * CustomerOrderStateCode (PK) A unique retailer assigned code denoting a
	 * potential state for a CustomerOrder. Eg: Create, DeleteItem, AddItem,
	 * ChangeItem, PartialDelivery, DeliveryComplete, PartialPickup,
	 * PickupComplete etc... CD_STE_CTORD Code2 char(2)
	 */
	String customerOrderStateCode;

	/**
	 * CustomerOrderStateName The name of the retailer assigned CustomerOrder
	 * state. NM_STE_CTORD Name varchar(40)
	 */
	String customerOrderStateName;

	public String getCustomerOrderStateCode() {
		return customerOrderStateCode;
	}

	public void setCustomerOrderStateCode(String customerOrderStateCode) {
		this.customerOrderStateCode = customerOrderStateCode;
	}

	public String getCustomerOrderStateName() {
		return customerOrderStateName;
	}

	public void setCustomerOrderStateName(String customerOrderStateName) {
		this.customerOrderStateName = customerOrderStateName;
	}

}
