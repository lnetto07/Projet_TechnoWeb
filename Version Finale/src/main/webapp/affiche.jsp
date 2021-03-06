<%@page import="simplejdbc.FCompany"%>
<%@page import="simplejdbc.OrderEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date" %>

<!DOCTYPE html>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
        <meta charset="UTF-8" />
        <title>Vous êtes connecté</title>
        <link rel="stylesheet" media="screen" href="CSS/pageconnecté.css" type="text/css" />
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
                <h2>Bonjour ${userName}</h2>
            </div>
            <div class="listecomm">
                <h3> Listes des commandes </h3>
                <form action='OrderController' method='POST'>
                    <button type="submit" class="btn" name="action" value="ajout">Ajouter une commande</button>
                </form>
            </div>
            <table border ="0">
                <thead>
                <th>Numéro</th>
                <th>Produit</th>
                <th>Quantité</th>
                <th>Prix</th>
                <th>Date de commande</th>
                <th>Date d'envoi</th>
                <th>Compagnie d'envoie</th>
                </thead>
                <tbody>

                    <%
                        //Afficher les commandes
                        List<OrderEntity> listeCommandes = (List<OrderEntity>) request.getAttribute("listCommandes");

                        for (OrderEntity e : listeCommandes) {
                            out.println("<tr>");
                            out.println("<td>");
                            int idCommande = e.getOrderId();
                            out.println(idCommande);
                            out.println("</td>");

                            out.println("<td>");
                            int idProduct = e.getProductId();
                            String name = e.getProductName(idProduct);
                            out.println(name);
                            out.println("</td>");

                            out.println("<td>");
                            int q = e.getQty();
                            out.println(q);
                            out.println("</td>");

                            out.println("<td>");
                            float prix = e.calculPrixTot(idCommande);
                            out.println(prix);
                            out.println("</td>");

                            out.println("<td>");
                            String d1 = e.getSalesDate();
                            out.println(d1);
                            out.println("</td>");

                            out.println("<td>");
                            String d2 = e.getShipDate();
                            out.println(d2);
                            out.println("</td>");

                            out.println("<td>");
                            String comp = e.getFCompany().toString();
                            out.println(comp);
                            out.println("</td>");

                            out.println("<td>");
                            out.println("<form action='OrderController' method='POST'>");
                            out.println("<input name='prodId' type='hidden' value='" + idCommande + "'>");
                            
                            out.println("<button type='submit' class='btn' name='action' value='supprimer' >Supprimer</button>");
                            out.println("</td>");
                            
                            out.println("<td>");
                            out.println("<button type='submit' class='btn' name='action' value='modif'>Modifier</button>");
                            out.println("</td>");
                            
                            out.println("</form >");
                            out.println("</tr>");
                        }
                    %>

            </table> 
        </tbody>
    </table>
    
    <script>
        //Confirmer la suppression, n'a pas été mis en place
        function myFunction() {
            var txt;
            if (confirm("Confirmez vous la suppression de votre commande ?")) {
                txt = "Confirmer";
            } else {
                txt = "Annuler";
            }
            document.getElementById("suppression").innerHTML = txt;
        }
    </script>


</div>


</section>
</body>

</html>
