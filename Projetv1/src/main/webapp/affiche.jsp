<%@page import="simplejdbc.OrderEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
    La servlet fait : session.setAttribute("customer", customer)
    La JSP récupère cette valeur dans ${customer}
--%>

<html>

    <head>
        <meta charset="UTF-8" />
        <title>Vous êtes connecté</title>
        <link rel="stylesheet" media="screen" href="pageconnecté.css" type="text/css" />
    </head>

    <body>
        <section id="accueil">
            <form>
                <div class="titre">
                    <h2>Bonjour ${userName}</h2>
                </div>
                <div class="listecomm">
                    <h3> Listes des commandes effectuées </h3>
                    <button type="submit" class="btn">Voir mes commandes</button>
                </div>
                
              <%    List<OrderEntity> listeCommandes = (List<OrderEntity>) request.getAttribute("listCommandes");
                    int cpt=1;
                    for(OrderEntity e : listeCommandes){
                        out.println("<div class='comm'>");
                        out.println("<h4>Commande "+cpt+"</h4>");
      }
%>
      <div class="comm">
        <h4>Commande 1</h4>
        <h5>Numéro</h5>
        <h5>Produit</h5>
        <h5>Date</h5>
        <button type="submit" class="btn">Modifier</button>
        <button type="submit" class="btn">Supprimer</button>
      </div>
      
      <div class="comm">
        <h4>Commande 1</h4>
        <h5>Numéro</h5>
        <h5>Produit</h5>
        <h5>Date</h5>
        <button type="submit" class="btn">Modifier</button>
        <button type="submit" class="btn">Supprimer</button>
      </div>
    </form>

  </section>
</body>

</html>