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
import sensos.bo.social.ExternalToken;

/**
 *
 * @author sensos
 */
@Remote
public interface IExternalTokenManager {
   
        /**
     * Creates externalToken (used during authentication process).
     * @param externalAccessToken
     * @return 
     */
    public ExternalToken createExternalToken(ExternalToken externalAccessToken);

    /**
     * Removes externalToken (used during authentication process). 
     * @param id 
     */
    public void removeExternalToken(Long id);
    

    /**
     * Finds externalToken.
     * @param name          name of the class that executes the authentication.
     * @param type          the type of the token.
     * @param requestToken  token that is sent during callback process.
     * @return 
     */
    public ExternalToken findExternalToken(String name, ExternalToken.Type type, String requestToken);

    
}
