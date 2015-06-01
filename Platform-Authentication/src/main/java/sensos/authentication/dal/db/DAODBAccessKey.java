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

package sensos.authentication.dal.db;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;

public class DAODBAccessKey extends DAODB {

    Logger logger = Logger.getLogger(DAODBAccessKey.class.getName());
	
    public DAODBAccessKey() {  }

    public DAODBAccessKey(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }
	
	public PrivateAccessKey retrieveById(PrivateAccessKey accessKey) {
		
        return em.find(PrivateAccessKey.class, accessKey.getId());
		
	}

	public PrivateAccessKey retrieveByKey(PrivateAccessKey accessKey) {

        try {

        	accessKey = em.createNamedQuery("PrivateAccessKey.findByKey", PrivateAccessKey.class)
                    .setParameter("accessKey", accessKey.getAccessKey())
                    .getSingleResult();

        } catch (Exception e) {
        	
        	e.printStackTrace();

        }
		
		return null;
		
	}

	/**
	 * 
	 * @param accessKey
	 */
	public void store(PrivateAccessKey accessKey) {

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(accessKey);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();
		
		
	}

    public List<PublicAccessKey> getPublicAccessKeys(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
