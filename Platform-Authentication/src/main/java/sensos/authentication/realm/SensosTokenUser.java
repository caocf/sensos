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
package sensos.authentication.realm;

import java.security.Principal;
import java.util.*;

/**
 * Represents a FileRealm user.
 *
 *
 */
public class SensosTokenUser implements Principal {
	private static final String GROUP_KEY = "Groups"; // not needed
	private String[] groups;
	private Hashtable<String, String[]> attributes;

	private byte[] salt;
	private byte[] hash;

	/**
	 * Constructor.
	 *
	 */
	public SensosTokenUser(String name) {
		// super(name);
		attributes = new Hashtable<String, String[]>(1); // not really needed
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            User name.
	 * @param groups
	 *            Group memerships.
	 * @param realm
	 *            Realm.
	 * @param salt
	 *            SSHA salt.
	 * @param hash
	 *            SSHA password hash.
	 *
	 */
	public SensosTokenUser(String name, String[] groups, String realm,
			byte[] salt, byte[] hash) {
		// super(name);
		this.groups = groups;
		this.hash = hash;
		this.salt = salt;

		attributes = new Hashtable<String, String[]>(1); // not really needed
		attributes.put(GROUP_KEY, groups);
	}

	/**
	 * Returns salt value.
	 *
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * Set salt value.
	 *
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	/**
	 * Get hash value.
	 *
	 */
	public byte[] getHash() {
		return hash;
	}

	/**
	 * Set hash value.
	 *
	 */
	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	/**
	 * Returns the realm with which this user is associated
	 *
	 * @return Realm name.
	 * @exception NoSuchRealmException
	 *                if the realm associated this user no longer exist
	 *
	 * 
	 *                public Realm getRealm() throws NoSuchRealmException {
	 *                return Realm.getInstance(realm); }
	 */

	/**
	 * Return the names of the groups this user belongs to.
	 *
	 * @return String[] List of group memberships.
	 *
	 */
	public String[] getGroups() {
		return groups;
	}

	/**
	 * Set group membership.
	 *
	 */
	public void setGroups(Vector<?> grp) {
		String[] g = new String[grp.size()];
		grp.toArray(g);
		this.groups = g;
		attributes.put(GROUP_KEY, groups);
	}

	/**
	 * Set group membership.
	 *
	 */
	public void setGroups(String[] grp) {
		this.groups = grp;
	}

	/**
	 * Return the requested attribute for the user.
	 * <P>
	 * Not really needed.
	 *
	 * @param key
	 *            string identifies the attribute.
	 */
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	/**
	 * Return the names of the supported attributes for this user.
	 * <P>
	 * Not really needed.
	 */
	public Enumeration<String> getAttributeNames() {
		return attributes.keys();
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

}