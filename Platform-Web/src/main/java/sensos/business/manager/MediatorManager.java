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

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.DependsOn;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sensos.bo.mediator.BillableUnit;
import sensos.contracts.mediator.IMediatorManager;
import sensos.dal.db.DAODBBillableUnit;

/**
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(IMediatorManager.class)
@DependsOn("Manager")
public class MediatorManager implements IMediatorManager {

    private static DAODBBillableUnit daoBillableUnit;

    @PostConstruct
    public void init() {
        daoBillableUnit = Manager.factoryPlatform.getDAOBillableUnit();
    }

    @Override
    @Asynchronous
    public void receive(BillableUnit b) {

        daoBillableUnit.store(b);
        
    }

}
