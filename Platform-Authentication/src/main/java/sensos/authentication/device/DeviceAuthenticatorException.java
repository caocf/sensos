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
package sensos.authentication.device;

/**
 * Provides exception handling for the authentication.
 * 
 * TODO: This class should handle, for example, account locking mechanisms if
 * intrusion is detected.
 * 
 * 
 * @author sensos
 */
public class DeviceAuthenticatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4768426473276864791L;

	public DeviceAuthenticatorException() {
		// TODO: Design Exception class
	}

	public DeviceAuthenticatorException(String message) {

		super(message);

	}

	public DeviceAuthenticatorException(Exception e) {
		super(e);
	}

}