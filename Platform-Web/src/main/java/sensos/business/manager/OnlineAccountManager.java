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
import sensos.bo.social.OnlineAccount;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IOnlineAccountManager;
import sensos.dal.db.DAODBOnlineAccount;

/**
 * Implementation to manage Application components
 *
 * @see sensos.contracts.business.IApplicationManager
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(IOnlineAccountManager.class)
@DependsOn("Manager")
public class OnlineAccountManager implements IOnlineAccountManager {

    private static DAODBOnlineAccount daoOnlineAccount;

    public OnlineAccountManager() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        daoOnlineAccount = Manager.factoryPlatform.getDAOOnlineAccount();
    }

    @Override
    @Asynchronous
    public void storeOnlineAccount(OnlineAccount onlineAccount) {
        
        daoOnlineAccount.storeOnlineAccount(onlineAccount);
        
    }

    @Override
    public OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount) {
        
        return daoOnlineAccount.updateOnlineAccount(onlineAccount);
        
    }

    @Override
    @Asynchronous
    public void removeOnlineAccount(OnlineAccount onlineAccount) {
        
        daoOnlineAccount.removeOnlineAccount(onlineAccount);
        
    }

    @Override
    public OnlineAccount findOnlineAccount(String appKey, String name, String id) {
        
        return daoOnlineAccount.findOnlineAccount(appKey, name, id);
        
    }

    @Override
    public void bindOnlineAccount(PlatformUser platformUser, OnlineAccount onlineAccount) {
        
        daoOnlineAccount.bindOnlineAccount(platformUser, onlineAccount);
        
    }

}
