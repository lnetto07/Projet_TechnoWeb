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

        // Quelle action a appelé cette servlet ?
        String action = request.getParameter("action");
        if (null != action) {
            switch (action) {
                case "client":
                    CAClient(request);
                    request.getRequestDispatcher("charts.jsp").forward(request, response);
                    break;
                case "produit":
                    CAProduit(request);
                    request.getRequestDispatcher("charts.jsp").forward(request, response);
                    break;
                case "zone":
                    CAZone(request);
                    request.getRequestDispatcher("chart.jsp").forward(request, response);
                    break;
                default:

            HttpSession session = request.getSession(false);
            session.setAttribute("dated",request.getAttribute("debut"));
            session.setAttribute("datef",request.getAttribute("fin"));
            request.setAttribute("graph","oui");
            request.getRequestDispatcher("googlePieChart.jsp").forward(request, response);
           
            
                            break;
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DAOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void CAClient(HttpServletRequest request) throws DAOException, SQLException {
        // Les paramètres transmis dans la requête
        String dateDebut = request.getParameter("débutC");
        String dateFin = request.getParameter("finC");
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HashMap CAClients = dao.CAClients(dateDebut, dateFin);
        request.setAttribute("Ca", CAClients);
        Set clients = CAClients.keySet();
        request.setAttribute("listeCli", clients);

    }

    private void CAProduit(HttpServletRequest request) throws SQLException {
        // On termine la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        String debut = request.getParameter("debut");
        String fin = request.getParameter("fin");
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        HashMap liste = dao.CAProduits(debut, fin);
        request.setAttribute("listeCA", liste);
        request.setAttribute("graph", "1");

    }

    private void CAZone(HttpServletRequest request) throws DAOException, SQLException {
        // Les paramètres transmis dans la requête
        String loginParam = request.getParameter("loginParam");
        String passwordParam = request.getParameter("passwordParam");

        DAO dao = new DAO(DataSourceFactory.getDataSource());
        String adL = getInitParameter("adminL");
        String adP = getInitParameter("adminP");
        if (loginParam.equals(adL)) {
            if (passwordParam.equals(adP)) {

                HttpSession session = request.getSession(true); // démarre la session
                session.setAttribute("userName", "admin");

            } else {
                request.setAttribute("errorMessage", "Login/Password incorrect");
            }

        } else {
            int pass = dao.loginCustomer(loginParam, Integer.parseInt(passwordParam));

            if (pass == (Integer.parseInt(passwordParam))) {
                // On a trouvé la combinaison login / password
                // On stocke l'information dans la session
                HttpSession session = request.getSession(true); // démarre la session
                String name = dao.selectNomByEmail(loginParam);
                session.setAttribute("userName", name);
                List<OrderEntity> commandeCli = dao.commandesExistantes(loginParam);
                request.setAttribute("listCommandes", commandeCli);

            } else // On positionne un message d'erreur pour l'afficher dans la JSP
            {
                request.setAttribute("errorMessage", "Login/Password incorrect");
            }
        }
    }

}
