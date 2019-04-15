<%-- 
    Document   : modifierCommande
    Created on : 4 avr. 2019, 16:31:39
    Author     : Evelyne Rwalinda
--%>

<%@page import="simplejdbc.FCompany"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8" />
        <title>Commande 1</title>
        <link rel="stylesheet" media="screen" href="CSS/modifierCommande.css" type="text/css" />
    </head>
    <body>
        <section id="accueil">
            <header>
                <nav>
                    <ul>
                        <form action='OrderController' method='POST'>
                            <li><button type="submit" name="action" value="accueil" id="nav1" >Accueil</button></li>
                            <li><button type="submit" name="action" value="accueil" id="nav2">Mes Commandes</button></li>
                            <li><button type="submit" name="action" value="ajout" id=" nav3">Faire une commande</button></li>
                            <li><button type='submit' name='action' value='logout'>Déconnexion</button></li>
                        </form>
                    </ul>
                </nav>
            </header>
            <div class="titre">
                <h2>Commande 1</h2>
            </div>
            <form action="<c:url value="OrderController" />" method="POST">
                <div class="infocomm">
                    <h3> Information commande </h3>
                    <label for="numéro">Numéro : ${commande.getOrderId()}</label> 
                    <input name='orderId' type='hidden' value=${commande.getOrderId()}>
                    <br>
                    <br>
                    <label for="produit">Produit : ${commande.getProductName(commande.getProductId())}</label> 
                    <br>
                    <br>
                    <label for="idProduit">Id produit : ${commande.getProductId()} </label>  
                    <br>
                    <br>
                    <label for="quantité">Quantité :</label> <input type="text" name="quantity" pattern="[1-9][0-9]*" value=${commande.getQty()} required> 
                    
                    <br>
                    <br>
                    <label for="ncli">Nom client : ${Nom}</label>  
                    <br>
                    <br>
                    <label for="compagnie">Compagnie d'envoi : </label>
                    <select id="compagnie" name="compagnie">
                        <%
                            for (FCompany c : FCompany.values()) {
                                out.println("<option");
                                String comp = (String) request.getAttribute("compagnie");
                                comp = comp.replace(" ", "_");
                                FCompany f = FCompany.valueOf(comp);
                                if (c == f) {
                                    out.println("selected='selected'");
                                }
                                out.println(">");
                                out.println(c.toString().replace("_"," "));
                                out.println("</option>");

                            }
                        %>


                    </select>
                    <br>
                    <br>
                    <label for="coutcom">Cout de la commande : ${commande.calculPrix(commande.getOrderId())}</label> 
                    <br>
                    <br>
                    <label for="coutdenvoi">Cout d'envoi : ${commande.getShipCost()}</label> 
                    <br>
                    <br>
                    <label for="coutTotal">Cout total : ${commande.calculPrixTot(commande.getOrderId())}</label> 
                    <br>
                    <br>
                    <label for="dateCom">Date de commande : ${commande.getSalesDate()}</label>  
                    <br>
                    <br>
                    <label for="dateEnvoi">Date d'envoi : ${commande.getShipDate()}  <required> 
                            <br>

                            <br>
                            </div>


                            <div class="button">
                                <button type="submit" name='action' class="btn" value='modifier'>Valider</button>
                            </div>
                            </form>
                            </section>
                            </body>
                            </html>
