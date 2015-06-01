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
package sensos.dock.rest.v1;

import java.io.IOException;
import javax.jms.JMSException;

/**
 *
 * @author sensos
 */
public class ServiceException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5134775969002269216L;

	public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(JMSException e) {
        super(e);
    }

    public ServiceException(IOException e) {
        super(e);
    }
    
    public ServiceException(SecurityException e) {
        super(e);
    }

}
