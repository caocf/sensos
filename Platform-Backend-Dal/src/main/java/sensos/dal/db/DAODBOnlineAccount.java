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
package sensos.dal.db;

import sensos.dal.DAODB;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sensos.bo.social.OnlineAccount;
import sensos.bo.user.PlatformUser;

/**
 *
 * @author sensos
 */
public class DAODBOnlineAccount extends DAODB {

    private Logger l = Logger.getLogger(this.getClass().getName());

    public DAODBOnlineAccount() {
    }

    public DAODBOnlineAccount(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }

    public OnlineAccount findOnlineAccount(String appKey, String network, String accountId) {

        OnlineAccount onlineAccount = null;

        try {
            onlineAccount = em.createNamedQuery("OnlineAccount.findByAppKeyAndNetworkAndAccountId", OnlineAccount.class)
                    .setParameter("appKey", appKey)
                    .setParameter("network", network)
                    .setParameter("accountId", accountId).getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return onlineAccount;

    }

    public void removeOnlineAccount(OnlineAccount onlineAccount) {

        em.getTransaction().begin();
        onlineAccount = em.merge(onlineAccount);
        em.remove(onlineAccount);
        em.getTransaction().commit();

    }

    public OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void storeOnlineAccount(OnlineAccount onlineAccount) {

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(onlineAccount);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();

    }

    public void bindOnlineAccount(PlatformUser platformUser, OnlineAccount onlineAccount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
