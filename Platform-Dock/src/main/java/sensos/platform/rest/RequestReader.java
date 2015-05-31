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
package sensos.platform.rest;

import java.io.IOException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sensos
 */
public class RequestReader {

    /**
     * Reads all content from the requests and closes the stream.
     *
     * @param request
     * @return a content for the user.
     */
    public StringBuilder readRequest(HttpServletRequest request) {

        ServletInputStream sis = null;
        StringBuilder sb = new StringBuilder();

        try {
            //Read the context of the request.
            //Cut the read if the request is too lenghty.
            sis = request.getInputStream();

            int i = 0;

            while ((i = sis.read()) >= 0) {

                sb.append((char) i);

                //TODO: Cut the read if the request is too leghty

            }

            sis.close();

        } catch (IOException e) {
            //TODO: Design appropriate exception handling...
        } finally {
        }

        return sb;

    }
    
}
