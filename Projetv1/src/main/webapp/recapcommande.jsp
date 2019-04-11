<%-- 
    Document   : recapcommande
    Created on : 4 avr. 2019, 16:31:39
    Author     : Evelyne Rwalinda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8" />
        <title>Commande 1</title>
        <link rel="stylesheet" media="screen" href="récapcommande.css" type="text/css" />
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="#accueil">Accueil</a></li>
                    <li><a href="#equipe">Mes commandes</a></li>
                    <li><a href="#ressources">Faire une commande</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </nav>
        </header>
        
        <section id="accueil">
            <div class="titre">
                <h2>Commande 1</h2>
            </div>
            <form>

                <div class="infocomm">
                    <h3> Information commande </h3>
                    <label for="numéro">Numéro :</label> <input type="text" required>
                    <br>
                    <br>
                    <label for="produit">Produit :</label> <input type="text" required>
                    <br>
                    <br>
                    <label for="quantié">Quantité :</label> <input type="text" required> 
                    <br>
                    <br>
                </div>

                <div class="infoclient">
                    <h3> Information Client </h3>
                    <label for="nom">Nom :</label> <input type="text" required>
                    <br>
                    <br>
                    <label for="adresse">Adresse :</label> <input type="text" required>
                    <br>
                    <br>
                </div>
                <div class="button">
                    <button type="submit" class="btn" value='submit'>Valider</button>
                </div>
            </form>
        </section>
    </body>
</html>