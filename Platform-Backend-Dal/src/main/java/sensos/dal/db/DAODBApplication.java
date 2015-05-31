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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sensos.bo.app.Application;

/**
 * DAO to manage applications. 
 * @author sensos
 */
public class DAODBApplication extends DAODB {

    /**
     * 
     */
    public DAODBApplication() { }

    /**
     * 
     * @param _emFactory 
     */
    public DAODBApplication(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }
    
    /**
     * 
     * @param a 
     */
    public void store(Application a) {
        
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(a);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();
        
    }
    
    public Application retrieve(Application a) {
        
        return em.find(Application.class, a.getId());
        
    }

    /**
     * Get applications by creator.
     * 
     * @param userId
     * @return 
     */
    public List<Application> getApplications(Long userId) {
        
        return em.createNamedQuery("Application.findByCreator", Application.class)
        .setParameter("creator", userId)
        .getResultList();
                
    }

    /**
     * 
     * @param userId
     * @return 
     */
    public List<Long> getApplicationIds(Long userId) {

         return em.createNamedQuery("Application.findApplicationIds", Long.class)
        .setParameter("userId", userId)
        .getResultList();
        
        
    }

    /**
     * 
     * @param displayname
     * @return 
     */
    public Application getApplication(String urlName) {
        
        return em.createNamedQuery("Application.findByDisplayName", Application.class)
                .setParameter("urlName", urlName)
                .getSingleResult();
        
    }
    
}