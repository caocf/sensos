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
import javax.persistence.EntityManagerFactory;

import sensos.authentication.bo.PrivateAccessKey;
import sensos.authentication.bo.PublicAccessKey;

public class DAODBAccessKey {

	public DAODBAccessKey(EntityManagerFactory factory) {
		// TODO Auto-generated constructor stub
	}

	public PrivateAccessKey retrieveById(PrivateAccessKey accessKey) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrivateAccessKey retrieveByKey(PrivateAccessKey accessKey) {
		// TODO Auto-generated method stub
		return null;
	}

	public void store(PrivateAccessKey accessKey) {
		// TODO Auto-generated method stub
		
	}

    public List<PublicAccessKey> getPublicAccessKeys(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
