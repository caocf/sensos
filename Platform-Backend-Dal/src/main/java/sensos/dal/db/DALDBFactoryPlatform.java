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

import sensos.authentication.dal.db.DAODBAccessKey;
import sensos.authentication.dal.db.DAODBAuthentication;
import sensos.dal.DALDBFactory;
import sensos.dal.DALDBFactoryType;
import sensos.dal.DAODB;

/**
 *
 * @author sensos
 * @param <T>
 */
public class DALDBFactoryPlatform extends DALDBFactory {
    
    public DALDBFactoryPlatform(DALDBFactoryType type) {
        
        super(type);
        
    }
    
    public DALDBFactoryPlatform(String puname, String username, String password, String url, String driver) {
        
        super(puname,username,password,url,driver);
        
    }
    
    public DALDBFactoryPlatform(String mode) {
        
        super(mode);
        
    }
    
    


    public DAODBUser getDAOUser() {

        return new DAODBUser(factory);

    }

    public DAODBApplication getDAOApplication() {

        return new DAODBApplication(factory);

    }

    public DAOExternalToken getDAOExternalToken() {

        return new DAOExternalToken(factory);

    }

    public DAODBOnlineAccount getDAOOnlineAccount() {
        
        return new DAODBOnlineAccount(factory);
        
    }
        
    public DAODBBillableUnit getDAOBillableUnit() {
        
        return new DAODBBillableUnit(factory);
        
    }

    public DAODBDevice getDAODevice() {
        return new DAODBDevice(factory);
    }

    public DAODBAuthentication getDAOAuthentication() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DAODBAccessKey getDAOAccessKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
