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
package sensos.util.logging;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sensos
 */
public class Logger {

    /**
     *
     * @param request
     * @param response
     */
    public void log(HttpServletRequest request, HttpServletResponse response) {

        Enumeration<?> p = request.getParameterNames();
        Enumeration<?> a = request.getAttributeNames();

        while (p.hasMoreElements()) {
            System.out.println("Parametername: " + (String) p.nextElement());
        }
        while (a.hasMoreElements()) {
            System.out.println("Attributename " + (String) a.nextElement());
        }

    }

}
