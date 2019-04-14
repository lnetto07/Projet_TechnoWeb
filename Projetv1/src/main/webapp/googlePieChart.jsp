<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Visualisation Google</title>
    <!-- On charge JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <!-- On charge l'API Google -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <link rel="stylesheet" media="screen" href="charts.css" type="text/css" />

    <script type="text/javascript">

        google.load("visualization", "1", {packages: ["corechart"]});

        // Après le chargement de la page, on fait l'appel AJAX
        var graph = "<%= (String) request.getAttribute("graph") %>";
        console.log(graph);
        if(graph==="oui")google.setOnLoadCallback(doAjax);

        function drawChart(dataArray) {
            var data = google.visualization.arrayToDataTable(dataArray);
            var options = {
                title: 'Ventes par client',
                is3D: true
            };
            var chart = new google.visualization.BarChart(document.getElementById('piechart'));
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
        
        <table border="">
            <form action="<c:url value="AdminController"/>" method="POST">
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
                    <th>
                        <div class="periode">
                            <label for="periode">Fin :</label>
                            <button type="submit" class="btn" name="action" value="test">Comparer les chiffres d'affaires</button>
                        </div>
                    </th>
                </tr>
            </thead>
            </form>
        </table>


        <!-- Le graphique apparaît ici -->
        <div id="piechart" style="width: 900px; height: 500px;"></div>
    </section>
</body>