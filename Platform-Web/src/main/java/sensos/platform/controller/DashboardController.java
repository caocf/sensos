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
package sensos.platform.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sensos
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/welcome",
    "/noaccess",
    "/manageDeviceAction",
    "/manageProfileAction",
    "/dashboardAction",
    "/jsonAction",
    "/websocketAction"})
public class DashboardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String requestUri = request.getRequestURI();

        if (requestUri.endsWith("welcome")) {

            request.getRequestDispatcher("res/index.jsp").include(request, response);

        } else if (requestUri.endsWith("noaccess")) {

            request.getRequestDispatcher("noaccess.jsp").include(request, response);

        } else if (requestUri.endsWith("manageDeviceAction")) {

            request.getRequestDispatcher("manageDevice.jsp").include(request, response);

        } else if (requestUri.endsWith("manageProfileAction")) {

            request.getRequestDispatcher("manageProfile.jsp").include(request, response);

        } else if (requestUri.endsWith("websocketApplication")) {

            request.getRequestDispatcher("websocketApplication.jsp").include(request, response);

        } else if (requestUri.endsWith("jsonApplication")) {

            request.getRequestDispatcher("jsonApplication.jsp").include(request, response);

        } else {

            throw new IllegalStateException("Urlpatterns have not been correctly defined.");

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
