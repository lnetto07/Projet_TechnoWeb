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
  <link rel="stylesheet" media="screen" href="pageconnecté.css" type="text/css" />
</head>

<body>
  <section id="accueil">
    <form>
      <div class="titre">
        <h2>Bonjour M.UNTEL</h2>
      </div>
      <div class="listecomm">
        <h3> Listes des commandes </h3>
        <button type="submit" class="btn">Voir mes commandes</button>
      </div>
      <table border ="0">
        <thead>
          <th>Numéro</th>
          <th>Produit</th>
          <th>Quantité</th>
          <th>Prix</th>
          <th>Date de commande</th>
          <th>Date d'envoi</th>
        </thead>
        <tbody>
          <%
           List<OrderEntity> listeCommandes = (List<OrderEntity>) request.getAttribute("listCommandes");

              for (OrderEntity e : listeCommandes) {
                     out.println("<tr>");
                     out.println("<td>");
                    int idCommande = e.getOrderId();
//                    //selectDescription prod
                    out.println(idCommande);
                    out.println("</td>");
                    out.println("<td>");
                    int idProduct = e.getProductId();
                    out.println(idProduct);
                    out.println("</td>");
                    out.println("<td>");
                    int q = e.getQty();
                    out.println(q);
                    out.println("</td>");
                    out.println("<td>");
                    float prix= e.calculPrixTot(idCommande);
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

                    out.println("<form action='NewServlet' method='POST'>");
                    out.println("<input name='prodId' type='hidden' value='" + idCommande + "'>");

                    out.println("<button type='submit' class='btn' name='action' value='test' >Supprimer</button>");
                    out.println("</form >");

                    out.println("</td>");

                    out.println("</tr>");

                }

                
            %>

        </table> 
        </tbody>
      </table>
        <button type="submit" class="btn">Modifier</button>

        <button class="btn" onclick="myFunction()">Supprimer</button>


        <p id="suppression"></p>
        <script>
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
    </form>

  </section>
</body>

</html>