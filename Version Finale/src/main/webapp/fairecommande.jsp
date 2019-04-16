<%-- 
    Document   : fairecommande
    Created on : 11 avr. 2019, 23:57:04
    Author     : alexa
--%>

<%@page import="simplejdbc.FCompany"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Commande en cours</title>
        <link rel="stylesheet" media="screen" href="CSS/fairecommande.css" type="text/css"
    </head>
    <body>
        <section id="accueil">
            <header>
                <nav>
                    <ul>
                        <form action='OrderController' method='POST'>
                            <li><button type="submit" name="action" value="accueil">Accueil</button></li>
                            <li><button type="submit" name="action" value="accueil">Mes Commandes</button></li>
                            <li><button type="submit" name="action" value="ajout">Faire une commande</button></li>
                            <li><button type='submit' name='action' value='logout'>Déconnexion</button></li>
                        </form>
                    </ul>
                </nav>
            </header>
            <div class="titre">
                <h2>Commande :</h2>
            </div>
            <form action="<c:url value="OrderController" />" method="POST">
                <div class="caractcomm">
                    <div class="listeprod">
                        <label for="produit">Choisissez votre produit :</label>
                        <select id="produit" name="produit">

                            <%
                                //Recuperation de la liste des produits depuis l'OrderController
                                List<String> listeProduits = (List<String>) request.getAttribute("listeProduits");

                                for (String p : listeProduits) {
                                    out.println("<option");
                                    out.println(" selected='selected'>");
                                    out.println(p);
                                    out.println("</option>");

                                }
                            %>


                        </select>
                    </div>
                    <div class="quantité">
                        <label for="quantité">Quantité</label> :
                        <input type="text" name="quantite" pattern="[1-9][0-9]*" placeholder="saisir la quantité" required />
                    </div>
                    <div class="listecompagnie">
                        <label for="compagnie">Choisissez votre compagnie d'envoi :</label>
                        <select id="compagnie" name="compagnie">
                            <%
                                //Recuperation des valeurs des compagnies depuis la classe FCompagny
                                for (FCompany c : FCompany.values()) {
                                    //Dans la classe FCompagny les " " sont des "_"
                                    String c1 = c.toString().replace("_", " ");
                                    out.println("<option>");
                                    out.println(c1);
                                    out.println("</option>");

                                }
                            %>

                        </select>
                    </div>
                </div>
                <div class="button">
                    <button type="submit" class="btn" name='action' value ='ajouter'>Valider</button>
                </div>
            </form>
        </section>
    </body>
</html>
