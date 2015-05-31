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
package sensos.business.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sensos.dal.DALDBFactoryType;
import sensos.dal.billing.DALDBFactoryBilling;
import sensos.dal.db.DALDBFactoryPlatform;

/**
 *
 * @author sensos
 */
@Singleton
@Startup
public class Manager implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2007694533961219433L;
	
	protected static DALDBFactoryPlatform factoryPlatform;
    protected static DALDBFactoryBilling  factoryBilling;

    public Manager() { }

    @PostConstruct
    public void init() {

        //
        // if factories are already open, do not do nothing...
        //
        
        if (factoryPlatform != null && factoryPlatform.isOpen()) {
            //do nothing...
        } else {
            factoryPlatform = new DALDBFactoryPlatform(DALDBFactoryType.Platform);
        }
        
        if(factoryBilling != null && factoryBilling.isOpen()) {
            //do nothing...
        } else {
            factoryBilling = new DALDBFactoryBilling(DALDBFactoryType.PlatformBilling);
        }

    }

}
