<%-- 
    Document   : fairecommande2
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
        <link rel="stylesheet" media="screen" href="fairecommande.css" type="text/css"
    </head>
    <body>
        <section id="accueil">
            <div class="titre">
                <h2>Commande :</h2>
            </div>
            <form action="<c:url value="OrderController" />" method="POST">
                <div class="caractcomm">
                    <div class="listeprod">
                        <label for="produit">Choisissez votre produit :</label>
                        <select id="produit" name="produit">
                            
                            <%
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
                        <input type="text" name="quantite" placeholder="saisir la quantité" />
                    </div>
                    <div class="listecompagnie">
                        <label for="compagnie">Choisissez votre compagnie d'envoi :</label>
                        <select id="compagnie" name="compagnie">
                            <%
                                for (FCompany c : FCompany.values()) {
                                    out.println("<option");
                                    out.println(" selected='selected'>");
                                    out.println(c);
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
