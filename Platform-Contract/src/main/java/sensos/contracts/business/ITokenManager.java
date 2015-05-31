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
package sensos.contracts.business;

import javax.ejb.Remote;

/**
 * Interface to generate tokens
 * @author sensos
 */
@Remote
public interface ITokenManager {

    static final long serialVersionUID = 1L;

    /**
     * Create temporary token that exists in system for n seconds.
     * Maximum time for token to exist is 300 seconds.
     * @param seconds
     * @return 
     */
    public String  createTemporaryToken(int seconds);
    /**
     * Checks whether token exists or not. After checked the token is removed from the stack.
     * @param token
     * @return 
     */
    public boolean removeTemporaryToken(String token);
    
}
