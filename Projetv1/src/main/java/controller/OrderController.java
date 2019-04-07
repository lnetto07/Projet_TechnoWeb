/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import simplejdbc.*;

/**
 *
 * @author alexa
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String action = request.getParameter("action");
        if (null != action) {
            switch (action) {
                case "test":
                    request.getRequestDispatcher("test.jsp").forward(request, response);
                    break;
                    
                case "ajout":
                    ajouterCommande(request);
                    request.getRequestDispatcher("fairecommande.jsp").forward(request, response);
                    break;
                    
                default:
                    supprimerCommande(request);
                    request.getRequestDispatcher("affiche.jsp").forward(request, response);

                    break;

            }
        }

    }

    private void supprimerCommande(HttpServletRequest request) throws SQLException {
        // Les paramètres transmis dans la requête
        int idCommande = Integer.parseInt(request.getParameter("prodId"));
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        dao.supprimerCommande(idCommande);
        String userName = findUserInSession(request);

        List<OrderEntity> commandeCli = dao.commandesExistantes(userName);
        request.setAttribute("listCommandes", commandeCli);

    }
    
    private void ajouterCommande(HttpServletRequest request) throws SQLException{
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        List<String> produits = dao.listeProduit();
        request.setAttribute("listeProduits", produits);
        
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
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

}

