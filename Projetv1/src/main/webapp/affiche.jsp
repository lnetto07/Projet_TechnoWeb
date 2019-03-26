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
                <link rel="stylesheet" media="screen" href="v3.css" type="text/css" />
	</head>
	<body>
                <section id="accueil">
    <form action="<c:url value="/"/>" method="POST">
      <div class="titre">
        <h2>Bonjour M.UNTEL</h2>
      </div>
      <div class="listecomm">
        <h3> Listes des commandes </h3>
        <button type="submit" class="btn">Voir mes commandes</button>
      </div>
      <div class="comm1">
        <h4> Commande 1</h4>
        <h5>Numéro</h5>
        <h5>Produit</h5>
        <h5>Date</h5>
        <button type="submit" class="btn">Modifier</button>
        <button type="submit" class="btn">Supprimer</button>
      </div>
      <div class="comm2">
        <h4> Commande 2</h4>
        <h5>Numéro</h5>
        <h5>Produit</h5>
        <h5>Date</h5>
        <button type="submit" class="btn">Modifier</button>
        <button type="submit" class="btn">Supprimer</button>
      </div>
      
      <div class ="deconnexion">
        <input type='submit' name='action' value='logout'>
      </div>

    </form>
    
      <div class ="nbConnexions">
        <h3>Il y a actuellement ${applicationScope.numberConnected} utilisateurs connectés</h3>
      </div>

  </section>		
	</body>
</html>
