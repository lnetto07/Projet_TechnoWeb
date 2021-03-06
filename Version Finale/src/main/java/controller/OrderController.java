/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String userName = findUserInSession(request);
        if (null == userName) { // L'utilisateur n'est pas connecté
            // On choisit la page de login
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            if (null != action) {
                switch (action) {
                    case "ajout":
                        ajouterCommande(request);
                        request.getRequestDispatcher("fairecommande.jsp").forward(request, response);
                        break;
                    case "ajouter":
                        validerAjout(request);
                        request.getRequestDispatcher("affiche.jsp").forward(request, response);
                        break;
                    case "accueil":
                        HttpSession session = request.getSession(false);
                        DAO dao = new DAO(DataSourceFactory.getDataSource());
                        String nom = (String) session.getAttribute("userName");
                        //On recupère les commandes a afficher
                        List<OrderEntity> commandeCli = dao.commandesExistantes(nom);
                        request.setAttribute("listCommandes", commandeCli);
                        request.getRequestDispatcher("affiche.jsp").forward(request, response);
                        break;
                    case "supprimer":
                        supprimerCommande(request);
                        request.getRequestDispatcher("affiche.jsp").forward(request, response);
                        break;
                    case "modif":
                        modifierCommande(request);
                        request.getRequestDispatcher("modifierCommande.jsp").forward(request, response);
                        break;
                    case "modifier":
                        validerModif(request);
                        request.getRequestDispatcher("affiche.jsp").forward(request, response);
                        break;
                    case "logout":
                        doLogout(request);
                        request.getRequestDispatcher("login.jsp").forward(request, response);

                }
            }
        }

    }

    //Supprimer la commande
    private void supprimerCommande(HttpServletRequest request) throws SQLException {
        // Les paramètres transmis dans la requête
        int idCommande = Integer.parseInt(request.getParameter("prodId"));
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        dao.supprimerCommande(idCommande);

        String userName = findUserInSession(request);
        //On recupère les commandes a afficher
        List<OrderEntity> commandeCli = dao.commandesExistantes(userName);
        request.setAttribute("listCommandes", commandeCli);

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

    //Afficher les infos de la commande à modifier
    private void modifierCommande(HttpServletRequest request) throws SQLException {
        int idCommande = Integer.parseInt(request.getParameter("prodId"));
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        OrderEntity commande = dao.selectCommande(idCommande);
        request.setAttribute("commande", commande);
        request.setAttribute("compagnie", commande.getFCompany());
        ProductEntity p = dao.selectProductById(commande.getProductId());
        request.setAttribute("Nom", (dao.selectNomById(commande.getCustomerId())));

    }

    //Ajouter la commande
    private void validerAjout(HttpServletRequest request) throws SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String today = dtf.format(localDate);
        LocalDate envoie = LocalDate.now().plusDays(7);
        String apres = dtf.format(envoie);

        DAO dao = new DAO(DataSourceFactory.getDataSource());
        String nomProd = request.getParameter("produit");
        int qte = Integer.valueOf(request.getParameter("quantite"));
        String comp = request.getParameter("compagnie");
        int idProd = dao.selectIdProd(nomProd);
        int idOrd = dao.numNewCommande();
        HttpSession session = request.getSession(false);
        String nomCli = (String) session.getAttribute("userName");
        int idCli = dao.selectIdClient(nomCli);
        OrderEntity o = new OrderEntity(idOrd, idCli, idProd, qte, 0, today, apres, comp);
        dao.ajoutCommande(o);
        List<OrderEntity> commandeCli = dao.commandesExistantes(nomCli);
        request.setAttribute("listCommandes", commandeCli);

    }
//Afficher le form pour ajouter

    private void ajouterCommande(HttpServletRequest request) throws SQLException {
        DAO dao = new DAO(DataSourceFactory.getDataSource());

        List<String> produits = dao.listeProduit();
        request.setAttribute("listeProduits", produits);

    }

    //Valider la modification
    private void validerModif(HttpServletRequest request) throws SQLException {
        int quantity = Integer.valueOf(request.getParameter("quantity"));
        FCompany compagnie;
        //Dans fCompagny les " " sont des "_" 
        compagnie = FCompany.valueOf(request.getParameter("compagnie").replace(" ", "_"));
        int orderId = Integer.valueOf(request.getParameter("orderId"));
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        dao.modifCommande(orderId, quantity, compagnie);
        //Recupération des commandes pour aller dans affiche.jsp
        HttpSession session = request.getSession(false);
        String nomCli = (String) session.getAttribute("userName");
        List<OrderEntity> commandeCli = dao.commandesExistantes(nomCli);
        request.setAttribute("listCommandes", commandeCli);

    }

    private void doLogout(HttpServletRequest request) {
        // On termine la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
