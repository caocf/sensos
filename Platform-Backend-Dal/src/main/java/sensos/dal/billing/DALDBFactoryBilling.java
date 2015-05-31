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
package sensos.dal.billing;

import sensos.dal.DALDBFactory;
import sensos.dal.DALDBFactoryType;

/**
 *
 * @author sensos
 */
public class DALDBFactoryBilling extends DALDBFactory {

    public DALDBFactoryBilling(DALDBFactoryType type) {

        super(type);

    }

    public DALDBFactoryBilling(String puname, String username, String password, String url, String driver) {

        super(puname, username, password, url, driver);

    }

    public DALDBFactoryBilling(String mode) {

        super(mode);

    }
    
        public DAODBCustomer getDAOCustomer() {

        return new DAODBCustomer(factory);

    }

}
