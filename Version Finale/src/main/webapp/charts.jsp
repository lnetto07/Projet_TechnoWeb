<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Visualisation Google</title>
    <!-- On charge JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- On charge l'API Google -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" media="screen" href="CSS/charts.css" type="text/css" />

    <script type="text/javascript">

        google.load("visualization", "1", {packages: ["corechart"]});

        // Après le chargement de la page, on fait l'appel AJAX
        var graph = "<%= (String) request.getAttribute("graph")%>";
        var date1 = "<%= (String) request.getAttribute("dated")%>";
        var date2 = "<%= (String) request.getAttribute("datef")%>";
        //graph set dans admin controller. Si = oui --> bouton cliqué
        if (graph === "oui") {
            google.setOnLoadCallback(doAjax1);
            google.setOnLoadCallback(doAjax2);
            google.setOnLoadCallback(doAjax3);
            //Permet de voir les dates sur la console pour le moment
            console.log('date début: '+date1+' date fin:'+date2+'</p>');
        }
//Chart 1 : Produit
        function drawChart1(dataArray) {
            var data = google.visualization.arrayToDataTable(dataArray);
            var options = {
                title: 'Chiffre d\'affaire en fonction de la catégorie d\'article',
                is3D: true
            };
            var chart = new google.visualization.PieChart(document.getElementById('graph1'));
            chart.draw(data, options);
        }

        function doAjax1() {
            $.ajax({
                url: "CAbyProduct",
                dataType: "json",
                success: // La fonction qui traite les résultats
                        function (result) {
                            // On reformate le résultat comme un tableau
                            var chartData = [];
                            // On met le descriptif des données
                            chartData.push(["Produit", "Chiffre d'affaire"]);
                            for (var client in result.records) {
                                chartData.push([client, result.records[client]]);
                            }
                            // On dessine le graphique
                            drawChart1(chartData);
                        },
                error: showError
            });
        }

//Chart 2 : zones
        function drawChart2(dataArray) {
            var data = google.visualization.arrayToDataTable(dataArray);
            var options = {
                title: 'Chiffre d\'affaire en fonction des zones',
                is3D: true
            };
            var chart = new google.visualization.PieChart(document.getElementById('graph2'));
            chart.draw(data, options);
        }

        function doAjax2() {
            $.ajax({
                url: "CAbyZone",
                dataType: "json",
                success: // La fonction qui traite les résultats
                        function (result) {
                            // On reformate le résultat comme un tableau
                            var chartData = [];
                            // On met le descriptif des données
                            chartData.push(["Produit", "Chiffre d'affaire"]);
                            for (var client in result.records) {
                                chartData.push([client, result.records[client]]);
                            }
                            // On dessine le graphique
                            drawChart2(chartData);
                        },
                error: showError
            });
        }

//Chart 3 : clients
        function drawChart3(dataArray) {
            var data = google.visualization.arrayToDataTable(dataArray);
            var options = {
                title: 'Chiffre d\'affaire en fonction des clients',
                is3D: true
            };
            var chart = new google.visualization.PieChart(document.getElementById('graph3'));
            chart.draw(data, options);
        }

        function doAjax3() {
            $.ajax({
                url: "CAbyClient",
                dataType: "json",
                success: // La fonction qui traite les résultats
                        function (result) {
                            // On reformate le résultat comme un tableau
                            var chartData = [];
                            // On met le descriptif des données
                            chartData.push(["Produit", "Chiffre d'affaire"]);
                            for (var client in result.records) {
                                chartData.push([client, result.records[client]]);
                            }
                            // On dessine le graphique
                            drawChart3(chartData);
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
                    <form action='OrderController' method='POST'>
                        <li><button type='submit' name='action' value='logout'>Déconnexion</button></li>
                    </form>
                </ul>
            </nav>
        </header>
        <h1>STATISTIQUES</h1>

        <table border="0">
            <form action="<c:url value="AdminController"/>" method="POST">
                <thead>
                    <tr>
                        <th>
                            <div class="periode">
                                <label for="periode">Début :</label> 

                                <input type="date" name="debutP" value="2011-04-20" min="2011-01-01" >
                            </div>
                        </th>
                        <th>
                            <div class="periode">
                                <label for="periode">Fin :</label>
                                <input type="date" name="finP" value="2011-05-20" min="2011-01-01" >
                            </div>
                        </th>
                        <th>
                            <div class="periode">
                                <button type="submit" class="btn" name="action" value="test">Comparer les chiffres d'affaires</button>
                            </div>
                        </th>
                    </tr>
                </thead>
            </form>
        </table>


        <!-- Les graphiques apparaissent ici -->
            
        <div id="graph1" style="width: 1000px; height: 2000px; margin: 5px">
        </div>  
        <div id="graph2" style="width: 1000px; height: 2000px; margin: 5px">
        </div>
        <div id="graph3" style="width: 1000px; height: 2000px; margin: 5px">
        </div>

    </section>
</body>