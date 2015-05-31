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

import javax.persistence.EntityManagerFactory;
import sensos.bo.mediator.BillableUnit;
import sensos.dal.DAODB;

/**
 *
 * @author sensos
 */
public class DAODBBillableUnit extends DAODB {
  
    
    /**
     * 
     * @param _emFactory 
     */
    public DAODBBillableUnit(EntityManagerFactory _emFactory) {

        super(_emFactory);

    }
    
        /**
     * 
     * @param a 
     */
    public void store(BillableUnit b) {
        
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        //persist accessKey
        em.persist(b);

        // Commit the transaction, which will cause the entity to be stored in the database
        em.getTransaction().commit();
        
    }
    
    
}
