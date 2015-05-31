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
import sensos.bo.social.ExternalToken;

/**
 *
 * @author sensos
 */
public class DAOExternalToken extends DAODB {

    private Logger l = Logger.getLogger(this.getClass().getName());

    public DAOExternalToken() {
    }

    public DAOExternalToken(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }

    public ExternalToken createExternalToken(ExternalToken externalAccessToken) {

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        try {
            //persist accessToken
            em.persist(externalAccessToken);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            // Commit the transaction, which will cause the entity to be stored in the database
            em.getTransaction().commit();
        }
        
        return externalAccessToken;

    }

    public ExternalToken findExternalToken(String name, ExternalToken.Type type, String requestToken) {
        ExternalToken externalToken = null;
        
        try {
        externalToken =  em.createNamedQuery("ExternalToken.findByNetworkAndTypeAndToken", ExternalToken.class)
                .setParameter("network", name)
                .setParameter("type", type)
                .setParameter("token", requestToken).getSingleResult();
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        return externalToken;
    }

    public void removeExternalToken(Long id) {
        
                
        ExternalToken externalToken = new ExternalToken();
        externalToken.setId(id);
        
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        try {
            
            //persist accessToken
            em.remove(externalToken);
            
        } finally {

            // Commit the transaction, which will cause the entity to be stored in the database
            em.getTransaction().commit();                
        
        }
        
        
    }

}
