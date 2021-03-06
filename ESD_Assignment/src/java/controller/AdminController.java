package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdminModel;

/**
 *
 * @author Adam Matheson
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List resultsList = new ArrayList();

        // Receive request from adminPage
        String c = request.getParameter("action");
        String mem_id = request.getParameter("mem_id");
        String id = request.getParameter("id");

        AdminModel am = new AdminModel();

        // Send to model & invoke one of three methods
        switch (c) {
            case "Check Approvals":
                resultsList = am.getApprovals();
                break;
            case "List Member Payments":
                resultsList = am.listPayments(mem_id);
                break;
            case "Approve Outstanding Member":
                am.approvalResult(mem_id);
                break;
            case "List Claims":
                resultsList = am.listClaims(id);
                break;
            case "Approve Claim":
                am.approveClaim(id);
                break;
            case "Reject Claim":
                am.rejectClaim(id);
                break;
            case "End of Year Charge":
                am.endOfYearCharge();
                break;
        }

        // Send back to view (adminPage.jsp)
        request.setAttribute("output", resultsList);
        RequestDispatcher view = request.getRequestDispatcher("/docs/adminPage");
        view.forward(request, response);
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
