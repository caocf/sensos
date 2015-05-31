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
package sensos.business.manager;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import sensos.bo.social.ExternalToken;
import sensos.contracts.business.IExternalTokenManager;
import sensos.dal.db.DAOExternalToken;

/**
 * Implementation to manage Application components
 *
 * @see sensos.contracts.business.IApplicationManager
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(IExternalTokenManager.class)
@DependsOn("Manager")
public class ExternalTokenManager implements IExternalTokenManager {

    private static DAOExternalToken externalToken;

    public ExternalTokenManager() {
    }

    @PostConstruct
    public void init() {
        externalToken = Manager.factoryPlatform.getDAOExternalToken();
    }

    /**
     * 
     */
    @Override
    public ExternalToken createExternalToken(ExternalToken externalAccessToken) {
        
        return externalToken.createExternalToken(externalAccessToken);

    }

    @Override
    @Asynchronous
    public void removeExternalToken(Long id) {
        
        externalToken.removeExternalToken(id);

    }

    @Override
    public ExternalToken findExternalToken(String name, ExternalToken.Type type, String requestToken) {
        
        return externalToken.findExternalToken(name, type, requestToken);
        
    }

}
