<%-- 
    Document   : fairecommande
    Created on : 4 avr. 2019, 15:52:25
    Author     : Evelyne Rwalinda
--%>

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
            <form action="<c:url value="/" />" method="POST">
                <div class="caractcomm">
                    <div class="listeprod">
                        <label for="produit">Choisissez votre produit :</label>
                        <select id="produit" name="produit">
                            <option value="1" selected="selected">Produit 1</option>
                            <option value="2" selected="selected">Produit 2</option>
                            <option value="3" selected="selected">Produit 3</option>
                        </select>
                    </div>
                    <div class="quantité">
                        <label for="quantité">Quantité</label> :
                        <input type="text" name="quantité" placeholder="saisir la quantié" />
                    </div>
                    <div class="listecompagnie">
                        <label for="compagnie">Choisissez votre compagnie d'envoi :</label>
                        <select id="compagnie" name="compagnie">
                            <option value="1" selected="selected">Compagnie 1</option>
                            <option value="2" selected="selected">Compagnie 2</option>
                            <option value="3" selected="selected">Compagnie 3</option>
                        </select>
                    </div>
                </div>
                <div class="prix">
                    <h3>Prix total :</h3>
                </div>
                <div class="button">
                    <button type="submit" class="btn" name='action' value ='submit'>Valider</button>
                </div>
            </form>
        </section>
    </body>
</html>
