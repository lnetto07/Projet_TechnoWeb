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
        <link rel="stylesheet" media="screen" href="recapcommande.css" type="text/css" />
    </head>
    <body>
        <section id="accueil">
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
            <div class="titre">
                <h2>Commande 1</h2>
            </div>

            <div class="infocomm">
                <h3> Information commande </h3>
                <label for="numéro">Numéro : ${numero}</label> 
                <br>
                <br>
                <label for="produit">Produit : ${Produit}</label> 
                <br>
                <br>
                <label for="idProduit">Id produit : ${IdProduit} </label>  
                <br>
                <br>
                <label for="quantité">Quantité :</label> <input type="number" id="quantite" value=${Quantite} required> 
                <input type="text" id="input"> oninput: <span id="result"></span>
                <script>
                    quantite.oninput = function () {
                        var p= (float) <%request.getAttribute("prixProd1");%>
                        result.innerHTML = (p);
                    };
                </script>
                <br>
                <br>
                <label for="ncli">Nom client : ${Nom}</label>  
                <br>
                <br>
                <label for="coutcom">Cout de la commande : ${PrixProduits}</label> 
                <br>
                <br>
                <label for="coutdenvoi">Cout d'envoi : ${Prixdenvoi}</label> 
                <br>
                <br>
                <label for="coutTotal">Cout total : ${CoutTotal}</label> 
                <br>
                <br>
                <label for="dateCom">Date de commande : ${Datedecommande}</label>  
                <br>
                <br>
                <label for="dateEnvoi">Date d'envoi : </label> <input type="text" value="${Datedenvoi}"  required> 
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
