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
package sensos.contracts.business;

import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import sensos.bo.app.Application;
import sensos.bo.user.PlatformUser;

/**
 * Contract to manage Applications.
 * @author sensos
 */
@Remote
public interface IApplicationManager {

    static final long serialVersionUID = 1L;
    
    public enum Role {
        
        User;
        
    }
    
    /**
     * Store application.
     * @param a 
     */
    @Asynchronous
    public void storeApplication(Application a);

    /**
     * Get application to particular application instance.
     * @param a
     * @return 
     */
    public Application getApplication(Application a);

    /**
     * Get new application instance by entering applicationId.
     * @param applicationId
     * @return 
     */
    public Application getApplication( Long applicationId );
    
    /**
     * Get application by urlName.
     * 
     * @param urlName is the unique name in the URL. For example: http://www.sensos.co.uk/platform/myApp where myApp is unique urlName.
     * @return 
     */
    public Application getApplication( String urlName );
    
    /**
     * Get applications for specific user.
     * @param userId
     * @return 
     */
    public List<Application> getApplications(Long userId);

    /**
     * Get applicationIds for specific user.
     * @param userId
     * @return 
     */
    public List<Long> getApplicationIds(Long userId);

    /**
     * Is user allowed to use a particular application in given role.
     * 
     * @param application   application that user wants to user.
     * @param user          an instance of application user.
     * @param role          check whether user is allowed to exist in such a role.,
     * @return 
     */
    public boolean isUserInRole(Application application, PlatformUser user, Role role);

}
