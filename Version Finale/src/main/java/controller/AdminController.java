/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;
import simplejdbc.OrderEntity;

/**
 *
 * @author Netto Léa
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws simplejdbc.DAOException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException, SQLException {

           //Verifie si l'utilisateur est connecté
        String userName = findUserInSession(request);
        //Si il n'est pas connecté ou n'est pas un admin
        if (null == userName || !userName.equals("admin")) { 
            // On choisit la page de login
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            
            String action = request.getParameter("action");
            if (null != action) {
                //Si il se déconnecte
                if (action.equals("logout")) {
                    doLogout(request);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                //Sinon on récupère les dates
                HttpSession session = request.getSession(false);
                session.setAttribute("dated", request.getParameter("debutP"));
                session.setAttribute("datef", request.getParameter("finP"));
                //Permet d'afficher dans la console de la page admin
                request.setAttribute("dated", request.getParameter("debutP"));
                request.setAttribute("datef", request.getParameter("finP"));
                request.setAttribute("graph", "oui");
                request.getRequestDispatcher("charts.jsp").forward(request, response);

            
        }
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
        try {
            try {
                processRequest(request, response);
            

} catch (SQLException ex) {
                Logger.getLogger(LoginController.class
.getName()).log(Level.SEVERE, null, ex);
            

}
        } catch (DAOException ex) {
            Logger.getLogger(LoginController.class
.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        

} catch (DAOException ex) {
            Logger.getLogger(LoginController.class
.getName()).log(Level.SEVERE, null, ex);
        

} catch (SQLException ex) {
            Logger.getLogger(LoginController.class
.getName()).log(Level.SEVERE, null, ex);
        }
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

  
    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("userName");
    }

private void doLogout(HttpServletRequest request) {
        // On termine la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}

