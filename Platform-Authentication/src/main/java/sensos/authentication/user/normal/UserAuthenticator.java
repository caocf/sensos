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
package sensos.authentication.user.normal;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensos.authentication.dal.db.DAODBAuthentication;
import sensos.authentication.misc.PasswordEncoder;

/**
 * Mechanism to execute login/password authentication.
 *
 * @author sensos
 */
public class UserAuthenticator {

	private Logger logger = Logger.getLogger(UserAuthenticator.class.getName());
	private DAODBAuthentication daoAuthentication;
	private PasswordEncoder passwordEncoder = new PasswordEncoder();

	public UserAuthenticator() {
	}

	public UserAuthenticator(DAODBAuthentication daoAuthentication) {
		this.daoAuthentication = daoAuthentication;
	}

	/**
	 * Authenticates user.
	 *
	 * @param username
	 * @param password
	 * @return
	 * @throws UserPasswordAuthenticatorException
	 */
	public boolean authenticate(String username, String password) throws UserPasswordAuthenticatorException {

		if (username == null) {
			throw new UserPasswordAuthenticatorException("No username defined");
		}

		if (password == null) {
			throw new UserPasswordAuthenticatorException("No password defined");
		}

		try {
			password = passwordEncoder.encode(password);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(UserAuthenticator.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UserAuthenticator.class.getName()).log(
					Level.SEVERE, null, ex);
		}

		return daoAuthentication.authenticate(username, password);

	}
}
