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
import sensos.bo.social.OnlineAccount;
import sensos.bo.user.PlatformUser;

/**
 *
 * @author sensos
 */
@Remote
public interface IOnlineAccountManager {

    /**
     * Stores onlineAccount.
     *
     * @param onlineAccount
     */
    public void storeOnlineAccount(OnlineAccount onlineAccount);

    /**
     * Updates online account.
     *
     * @param onlineAccount
     * @return
     */
    public OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount);

    /**
     * Removes online account.
     *
     * @param onlineAccount
     */
    public void removeOnlineAccount(OnlineAccount onlineAccount);

    /**
     * Finds onlineAccount.
     *
     * @param appKey application key.
     * @param name name of the class that executes authentication.
     * @param id userId in the external system.
     * @return
     */
    public OnlineAccount findOnlineAccount(String appKey, String name, String id);

    /**
     * Bind social account into existing user profile.
     *
     * @param platformUser
     * @param onlineAccount
     */
    public void bindOnlineAccount(PlatformUser platformUser, OnlineAccount onlineAccount);

}
