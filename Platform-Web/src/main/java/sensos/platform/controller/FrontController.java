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
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sensos.DockRoot;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.v1.JmsDRLMessage;
import static sensos.platform.controller.ControllerConstants.*;

/**
 *
 * @author sensos
 */
@WebServlet(name = "FrontController",
        urlPatterns = {
            "/login",
            "/logout"
        })
public class FrontController extends HttpServlet {

    private Logger l = Logger.getLogger(this.getClass().getName());

    private static boolean hasStarted = false;

    /**
     *
     * <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");

        l.log(Level.INFO, "is in role basic{0}", request.isUserInRole("Basic"));
        l.log(Level.INFO, "is in role HomePlus{0}", request.isUserInRole("HomePlus"));

        if (!hasStarted) {

            try {
                URL url = new URL(request.getRequestURL().toString());
                l.log(Level.INFO, "URL is {0}", url.toString());
                l.log(Level.INFO, "protocol is {0}", url.getProtocol());
                l.log(Level.INFO, "authority is {0}", url.getAuthority());
                l.log(Level.INFO, "file name is {0}", url.getFile());
                l.log(Level.INFO, "host is {0}", url.getHost());
                l.log(Level.INFO, "path is {0}", url.getPath());
                l.log(Level.INFO, "port is {0}", url.getPort());
                l.log(Level.INFO, "default port is {0}", url.getDefaultPort());
                l.log(Level.INFO, "query is {0}", url.getQuery());
                l.log(Level.INFO, "ref is {0}", url.getRef());
                l.log(Level.INFO, "document root is: {0}", DockRoot.getDocRoot());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                hasStarted = true;
            }

        }

        String requestUri = "";
        String queryString = "";

        requestUri = request.getRequestURI();
        queryString = request.getQueryString();

        if (requestUri == null) {
            throw new IllegalStateException("Request uri cannot be null");
        }

        // User has not been authenticated.
        // Read username and password from the request
        // 
        if (requestUri.endsWith("login")
                && request.getParameter("u") != null
                && request.getParameter("t") != null) {

            //
            // If user has been authenticated, redirectResource.
            //
            if (request.getUserPrincipal() != null) {

                l.log(Level.INFO, "User has been already authenticated. Redirecting resource.");

                redirectResource(request, response);

            }

            //
            // User has not been authenticated. Execute token validation.
            //
            try {

                l.log(Level.INFO, "User has not been authenticated. Executing token validation.");

                boolean authenticationSuccesful = executeAuthentication(request, response);

                if (authenticationSuccesful) {

                    l.log(Level.INFO, "User has been succesfully authenticated.");

                    l.log(Level.INFO, "Subscribing component.");

                    loginlogoutAction(request, JmsDRLMessage.Method.Logon);

                    redirectResource(request, response);

                } else {

                    l.log(Level.INFO, "User authentication failed. Redirecting back.");

                    redirectAuthentication(request, response);

                }

            } catch (Exception e) {

                request.setAttribute("error", e.getMessage());

                e.printStackTrace();

            }

        //
            // Logout user
            //
        } else if (requestUri.endsWith("logout")) {

            l.log(Level.INFO, "Logging out user...");

            loginlogoutAction(request, JmsDRLMessage.Method.Logoff);

            try {
                session.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            redirectAuthentication(request, response);

        } //
        // If user has been authenticated, redirectResource.
        //
        else if (request.getUserPrincipal() != null) {

            l.log(Level.INFO, "User has been already authenticated. Redirecting resource.");

            redirectResource(request, response);

        } //
        // User has not been authenticated and tries to access other resources.
        //
        else {

            l.log(Level.INFO, "User has not been authenticated. Forward to noaccess-page");

            //
            // Tell to no access what page the User tried to reach.
            //
            String originalRequestURI = "";
            String originalRequestQueryString = "";

            originalRequestURI = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);

            if (originalRequestURI != null) {
                originalRequestQueryString = (String) request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
            }

            request.setAttribute(ATTR_FORWARD_REQUEST_URI, originalRequestURI);
            request.setAttribute(ATTR_FORWARD_QUERY_STRING, originalRequestQueryString);

            redirectNoAccess(request, response);

        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);

    }

    /**
     * Send request to be authenticated.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void redirectAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/login");
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private boolean executeAuthentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request == null) {
            return false;
        }
        if (response == null) {
            return false;
        }

        String username = (String) request.getParameter("u");
        String token = (String) request.getParameter("t");

        try {

            request.login(username, token);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    /**
     * User has already authenticated. Delegate request to the FrontPage.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void redirectResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/welcome");

    }

    /**
     * Forward the request into the noaccess page.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void redirectNoAccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getServletContext().getContext("/").getRequestDispatcher("/noaccess.jsp").forward(request, response);

    }

    /**
     * Dispatches request redirectResource.
     *
     * @param request
     * @param response
     * @param page
     * @throws ServletException
     * @throws IOException
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {

        RequestDispatcher rd = request.getServletContext().getRequestDispatcher(page);
        rd.forward(request, response);

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void loginlogoutAction(HttpServletRequest request, JmsDRLMessage.Method method) throws ServletException, IOException {

        PlatformMessageRequest message = new PlatformMessageRequest();
        message.setAckRequired(false);
        JmsDRLMessage drlMessage = new JmsDRLMessage();

        drlMessage.setRemoteUser(request.getRemoteUser());
        drlMessage.setChannel(JmsDRLMessage.Channel.Web);
        drlMessage.setMethod(method);

        message.setPayload(drlMessage);

        /*
        try {
            SOADispatcher4.send(message);
        } catch (SOAException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

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
        return "Sensos FrontController";
    }// </editor-fold>
}
