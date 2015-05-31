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
package sensos.authentication.dal.db;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;
import sensos.authentication.misc.PasswordEncoder;

/**
 * DAO to manage AccessKeys.
 * 
 * @author sensos
 */
public class DAODBAuthentication extends DAODB {

	public DAODBAuthentication() {
	}

	public DAODBAuthentication(EntityManagerFactory _emFactory) {

		super(_emFactory);

	}

	/**
	 * Store PrivateAccessKey.
	 * 
	 * @param privateAccessKey
	 */
	public void store(PrivateAccessKey privateAccessKey) {

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// persist accessKey
		em.persist(privateAccessKey);

		// Commit the transaction, which will cause the entity to be stored in
		// the database
		em.getTransaction().commit();

	}

	/**
	 * Store PublicAccessKey.
	 * 
	 * @param publicAccessKey
	 */
	public void store(PublicAccessKey publicAccessKey) {

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// persist accessKey
		em.persist(publicAccessKey);

		// Commit the transaction, which will cause the entity to be stored in
		// the database
		em.getTransaction().commit();

	}

	/**
	 * Retrieve PrivateAccessKey by Id.
	 * 
	 * @param a
	 * @return
	 */
	public PrivateAccessKey retrieveById(PrivateAccessKey a) {

		return em.find(PrivateAccessKey.class, a.getId());

	}

	/**
	 * Retrieve PrivateAccessKey by Key.
	 * 
	 * @param a
	 * @return
	 */
	public PrivateAccessKey retrieveByKey(PrivateAccessKey a)
			throws NoResultException {

		if (a == null)
			throw new NoResultException("Incoming PrivateAccessKey is null.");

		return (PrivateAccessKey) em
				.createNamedQuery("PrivateAccessKey.findByKey")
				.setParameter("accessKey", a.getAccessKey()).getSingleResult();

	}

	/**
	 * For testing purposes.
	 * 
	 * @return
	 */
	public PrivateAccessKey retrieveFirstElement() {

		return (PrivateAccessKey) em.createNamedQuery(
				"PrivateAccessKey.findAll").getSingleResult();

	}

	/**
	 * Retrieve PublicAccessKeys for user.
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PublicAccessKey> getPublicAccessKeys(Long userId) {

		String sql = "SELECT * FROM PublicAccessKey ak ";
		sql += "LEFT JOIN Device d ON (ak.deviceId = d.id) ";
		sql += "WHERE d.owner = ?";

		Query q = em.createNativeQuery(sql, PublicAccessKey.class)
				.setParameter(1, userId);

		return q.getResultList();

	}

	/**
	 * Authenticate user according to Username and Password. This method is also
	 * used in BASIC-authentication.
	 * 
	 * First try to authenticate with username and password.. if authentication
	 * does not succeed, switch to email and password.
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean authenticate(String username, String password) {

		String incomingPasswordHash = null;
		String storedPasswordHash = null;

		if (username == null || "".equals(username))
			return false;
		if (password == null || "".equals(password))
			return false;

		//
		// Transform password to hash.
		//

		try {
			incomingPasswordHash = PasswordEncoder.getInstance().encode(
					password);
		} catch (NoSuchAlgorithmException | IOException ex) {
			Logger.getLogger(DAODBAuthentication.class.getName()).log(
					Level.SEVERE, null, ex);
		} finally {
			if (incomingPasswordHash == null) {
				return false;
			}
		}

		//
		// Get user
		//

		try {

			Query storedPasswordHashQuery = em
					.createNativeQuery(
							"Select u.passwordhash FROM PlatformUser u WHERE u.username = :username",
							String.class);
			storedPasswordHashQuery.setParameter("username", username);
			@SuppressWarnings("unchecked")
			List<String> result = storedPasswordHashQuery.getResultList();

			if (result == null)
				return false;
			if (result.size() > 1)
				throw new Exception("Found more that one user");
			else
				storedPasswordHash = result.get(0);

		} catch (Exception e) {

			Logger.getLogger(DAODBAuthentication.class.getName()).log(
					Level.SEVERE, null, e);

		}

		return storedPasswordHash != null
				&& incomingPasswordHash.equals(storedPasswordHash);

	}

}