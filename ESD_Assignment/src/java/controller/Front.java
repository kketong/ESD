/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DatabaseManager;

/**
 *
 * @author jacka
 */
public class Front extends HttpServlet {

    public static DatabaseManager dbm = new DatabaseManager("xyz_assoc");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id; // process Request URL
        String page = "/WEB-INF/docs/mainView.jsp"; // what page to forward to ...
        String include; // page to include into mainView.jsp

        // find last part of requested resource
        id = request.getRequestURI().substring(
                request.getContextPath().length());

        getServletContext().log("Front received a request for " + id);
        OUTER:
        switch (id) {
            case "/Front":
                include = "homePage.jsp";
                break;
            case "/docs/homePage":
                include = "homePage.jsp";
                break;
            case "/docs/loginPage":
                include = "loginPage.jsp";
                break;
            case "/docs/loginPage/registerNewUser":
                getServletContext().setAttribute("currentUser", dbm.registerNewMember(request.getParameter("rName"), request.getParameter("rAddress"), request.getParameter("rDOB")));
                include = "memberPage.jsp";
                break;
            case "/docs/memberPage":
                include = "memberPage.jsp";
                break;
            case "/docs/adminPage":
                include = "adminPage.jsp";
                break;
            case "/docs/tryLogin":                
                String status = dbm.verifyCredentials(request.getParameter("username"), request.getParameter("password"));
                switch (status) {
                    case "Invalid Password":
                    case "User not found":
                        include = "loginPage.jsp";
                        request.setAttribute("status", status);
                        break OUTER;
                    case "ADMIN":
                        include = "adminPage.jsp";
                        request.setAttribute("username", request.getParameter("username"));
                        break OUTER;
                    case "APPLIED":
                    case "APPROVED":
                    case "SUSPENDED":
                        include = "memberPage.jsp";
                        request.setAttribute("username", request.getParameter("username"));
                        break OUTER;
                }
            default:
                include = "error.jsp";
        }

        request.setAttribute("includedView", include);
        request.getRequestDispatcher(page).forward(request, response);
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
