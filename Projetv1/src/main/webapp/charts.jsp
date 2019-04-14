<%-- 
    Document   : charts
    Created on : 12 avr. 2019, 15:37:52
    Author     : lnetto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Array"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@ page language="java" import="com.google.gson.Gson" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8" />
        <title>Statistiques</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.charts.load('current', {packages: ['corechart']});
        </script>
        <link rel="stylesheet" media="screen" href="charts.css" type="text/css" />
        
                   <script type="text/javascript">
                google.load("visualization", "1", {packages: ["corechart"]});

                // Après le chargement de la page, on fait l'appel AJAX
                google.setOnLoadCallback(doAjax);

                function drawChart(dataArray) {
                    var data = google.visualization.arrayToDataTable(dataArray);
                    var options = {
                        title: 'Ventes par client',
                        is3D: true
                    };
                    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                    chart.draw(data, options);
                }

                // Afficher les ventes par client
                function doAjax() {
                    $.ajax({
                        url: "salesByCustomer",
                        dataType: "json",
                        success: // La fonction qui traite les résultats
                                function (result) {
                                    // On reformate le résultat comme un tableau
                                    var chartData = [];
                                    // On met le descriptif des données
                                    chartData.push(["Client", "Ventes"]);
                                    for (var client in result.records) {
                                        chartData.push([client, result.records[client]]);
                                    }
                                    // On dessine le graphique
                                    drawChart(chartData);
                                },
                        error: showError
                    });
                }

                // Fonction qui traite les erreurs de la requête
                function showError(xhr, status, message) {
                    alert("Erreur: " + status + " : " + message);
                }

            </script>
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
            <h1>STATISTIQUES</h1>
            <form action="<c:url value="AdminController"/>" method="POST"> 

                <table border="">
                    <thead>
                        <tr>
                            <th>
                                <div class="periode">
                                    <label for="periode">Début :</label>     
                                    <input type="date" name="debutP" value="2011-04-20" min="2011-01-01" max="2019-05-01">
                                </div>
                            </th>
                            <th>
                                <div class="periode">
                                    <label for="periode">Fin :</label>
                                    <input type="date" name="finP" value="2011-05-20" min="2011-01-01" max="2019-05-01">
                                </div>
                            </th>


                        </tr>
                    </thead>
                </table>
                <button type="submit" class="btn" name="action" value="produit">Comparer les chiffres d'affaires</button>
            </form>

            <div id="graph1" style="width: 550px; height: 400px; margin: 5px">
            </div>
 

            <table border="">
                <thead>
                    <tr>
                        <th>
                            <div class="periode">
                                <label for="periode">Début :</label>
                                <input type="date" name="debutZ">
                            </div>
                        </th>
                        <th>
                            <div class="periode">
                                <label for="periode">Fin :</label>
                                <input type="date" name="finZ">
                            </div>
                        </th>

                    </tr>
                </thead>
            </table>
            <form action='AdminController' method='POST'>
                <button type="submit" class="btn" name="action" value="zone">Comparer les chiffres d'affaires</button>
            </form>

            <div id="graph2" style="width: 550px; height: 400px; margin: 5px">
            </div>
            <script language="JavaScript">
                function drawChart() {

                    var data = google.visualization.arrayToDataTable([
                        ['Zone géographique', 'Chiffre d affaire'],
                        ['1', 900],
                        ['2', 1000],
                        ['3', 1170],
                    ]);

                    var options = {title: 'Chiffre d affaire en fonction de la zone géographique'};

                    var chart = new google.visualization.BarChart(document.getElementById('graph2'));
                    chart.draw(data, options);
                }
                google.charts.setOnLoadCallback(drawChart);
            </script>

            <table border="">
                <thead>
                    <tr>
                        <th>
                            <div class="periode">
                                <label for="periode">Début :</label>
                                <input type="date" name="debutC">
                            </div>
                        </th>
                        <th>
                            <div class="periode">
                                <label for="periode">Fin :</label>
                                <input type="date" name="finC">
                            </div>
                        </th>

                    </tr>
                </thead>
            </table>
            <form action='AdminController' method='POST'>
                <button type="submit" class="btn" name="action" value="client">Comparer les chiffres d'affaires</button>
            </form>


            <div id="graph3" style="width: 550px; height: 400px; margin: 5px">
            </div>
            <script language="JavaScript">
                <%

                    //        out.println("function drawChart(){ ");
                    //        out.println("var data = google.visualization.arrayToDataTable([['Client', 'Chiffre d affaire']]);");
                    ////ajouter les clients et ca à data
                    //        Set listeClients = (Set)request.getAttribute("listCli");
                    //        HashMap CACli=(HashMap) request.getAttribute("Ca");
                    //        String assos="";
                    //        for (Object c: listeClients){
                    //            assos=assos+", ["+(String)c+","+(String)CACli.get(c)+"]";
                    //        }
                    //        out.println("data.push("+assos+");");
                    //        out.println("var options = { title: 'Chiffre d affaire en fonction du client' };");
                    //        out.println("var chart = new google.visualization.BarChart(document.getElementById('graph3'));");
                    //        out.println("chart.draw(data, options);}");
%>
//                google.charts.setOnLoadCallback(drawChart);
            </script>


        </section>
    </body>

</html>