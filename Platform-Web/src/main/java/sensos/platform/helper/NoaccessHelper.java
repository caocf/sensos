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
package sensos.platform.helper;

import javax.servlet.http.HttpServletRequest;
import static sensos.platform.controller.ControllerConstants.*;

/**
 *
 * @author sensos
 */
public class NoaccessHelper {
    
    public static String getAccessedResource(HttpServletRequest request) {
        
        String requestUri = (String) request.getAttribute(ATTR_FORWARD_REQUEST_URI);
        String queryString =(String) request.getAttribute(ATTR_FORWARD_QUERY_STRING);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<h1>");
        sb.append("Requested uri: ");
        sb.append(requestUri);
        sb.append("</h1>");
        
        sb.append("<br/>");
        
        sb.append("<h1>");
        sb.append("Requested query: ");
        sb.append(queryString);
        sb.append("</h1>");
        
        return sb.toString();
        
    }
    
}
