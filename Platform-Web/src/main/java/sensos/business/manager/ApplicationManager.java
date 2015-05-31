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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import sensos.bo.app.Application;
import sensos.bo.user.PlatformUser;
import sensos.contracts.business.IApplicationManager;
import sensos.dal.db.DAODBApplication;


/**
 * Implementation to manage Application components
 *
 * @see sensos.contracts.business.IApplicationManager
 *
 * @author sensos
 */
@Singleton
@Startup
@Remote(IApplicationManager.class)
@DependsOn("Manager")
public class ApplicationManager implements IApplicationManager {

    private static DAODBApplication daoApplication;

    public ApplicationManager() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        daoApplication = Manager.factoryPlatform.getDAOApplication();
    }

    /**
     *
     * @param a
     */
    @Asynchronous
    public void storeApplication(Application a) {
        daoApplication.store(a);
    }

    /**
     *
     * @param a
     * @return
     */
    public Application getApplication(Application a) {

        return daoApplication.retrieve(a);

    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Application> getApplications(Long userId) {

        return daoApplication.getApplications(userId);

    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Long> getApplicationIds(Long userId) {

        return daoApplication.getApplicationIds(userId);

    }

    /**
     *
     * @param applicationId
     * @return
     */
    @Override
    public Application getApplication(Long applicationId) {

        Application a = new Application();
        a.setId(applicationId);

        return daoApplication.retrieve(a);
    }

    /**
     *
     * @param displayname
     * @return
     */
    @Override
    public Application getApplication(String displayname) {

        return daoApplication.getApplication(displayname);

    }

    /**
     *
     * @param application
     * @param user
     * @param role
     * @return
     */
    @Override
    public boolean isUserInRole(Application application, PlatformUser user, Role role) {

        //TODO!
        return true;

    }

}
