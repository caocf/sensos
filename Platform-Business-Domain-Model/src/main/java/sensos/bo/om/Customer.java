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
import java.util.Set;

/**
 *
 * A PartyRoleAssignment type that represents the association between a retailer
 * on one hand and an individual or organization (Party) on the other hand where
 * the party is a Consumer that has completed at least one purchase and whose
 * associated ConsumerConversionState indicates their status as a CUSTOMER. We
 * are using the term Consumerr to reflect the idea that this
 * PartyRoleAssignment represents parties that retailers are SELLING TO (or
 * trying to sell to). In other entities we differentiate between parties that
 * are in a pre-sale state (typically they're in the customer acquisition
 * funnel), an active state (they have purchased items and are ActiveCustomers)
 * , an inactive state (InactiveCustomers) or a dead state (ExCustomers).
 * 
 * @author sensos
 */
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7658060259746310155L;

	/**
	 * (PK) A unique system assigned identifier for a person or organization
	 * that purchases a product or service from the retailer. ID_CT Identity
	 * integer
	 */
	Long customerId;

	/**
	 * (FK) Token ID for a PartyConversionState instance. ID_CT_CVN_ST
	 * IdentityUUID char(32) ConsumerConversionState (CO_CT_CVN_ST)
	 */
	String consumerConversionStateID;

	/**
	 * (FK) A unique, system assigned identity for a Party. ID_PRTY Identity
	 * integer Party (PA_PRTY)
	 */
	int partyId;

	/**
	 * A boolean indicator that tells if this is an anonymous customer or not.
	 * If TRUE this is an anonymous customer that has no KeyCustomer (and
	 * related dependent information) and no PartyContactMethod information
	 * associated with it. FL_ANNYMS Flag integer
	 */
	int anonymousFlag;

	String username;

	/** Places */
	Set<CustomerOrder> customerOrders;

	public Customer() {
	}

	public Customer(Long id) {
		this.customerId = id;
	}

	public Customer(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long id) {
		this.customerId = id;
	}

}
