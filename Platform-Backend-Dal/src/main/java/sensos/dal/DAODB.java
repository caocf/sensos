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
package sensos.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Root for DAO-classes.
 * @author sensos
 */
public abstract class DAODB {
    
    protected EntityManagerFactory emFactory;
    protected EntityManager em;
    
    /**
     * 
     */
    public DAODB() { }
    
    /**
     * 
     * @param emFactory 
     */
    public DAODB(EntityManagerFactory emFactory) {
        
        this.emFactory = emFactory;
        
        em = emFactory.createEntityManager();
        
        if(em == null) {
         
        	throw new IllegalStateException("emFactory is null");
            
        }
        
    }
    
    /**
     * 
     * @throws Throwable 
     */
    @Override
    protected void finalize() throws Throwable {
    
        if(em != null && em.isOpen())
            em.close();
        super.finalize();
    
    }
    
}
