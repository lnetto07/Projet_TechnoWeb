<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
  <title>Statistiques</title>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
  </script>
  <script type="text/javascript">
    google.charts.load('current', { packages: ['corechart'] });     
  </script>
  <link rel="stylesheet" media="screen" href="charts.css" type="text/css" />
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
    <table border="0">
        <thead>
          <tr>
            <th>
              <div class="periode">
                <label for="periode">Début :</label>
                <select id="periode" name="periode">
                  <option value="1" selected="selected">1</option>
                  <option value="2" selected="selected">2</option>
                  <option value="3" selected="selected">3</option>
                </select>
              </div>
            </th>
            <th>
              <div class="periode">
                <label for="periode">Fin :</label>
                <select id="periode" name="periode">
                  <option value="1" selected="selected">1</option>
                  <option value="2" selected="selected">2</option>
                  <option value="3" selected="selected">3</option>
                </select>
              </div>
            </th>
          </tr>
        </thead>
      </table>


    <div id="graph1" style="width: 550px; height: 400px; margin: 5px">
    </div>
    <script language="JavaScript">
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Produit', 'Chiffre d affaire'],
          ['1', 900],
          ['2', 1000],
          ['3', 1170],

        ]);

        var options = { title: 'Chiffre d affaire en fonction de la catégorie d article' };

        var chart = new google.visualization.BarChart(document.getElementById('graph1'));
        chart.draw(data, options);
      }
      google.charts.setOnLoadCallback(drawChart);
    </script>

<table border="0">
    <thead>
      <tr>
        <th>
          <div class="periode">
            <label for="periode">Début :</label>
            <select id="periode" name="periode">
              <option value="1" selected="selected">1</option>
              <option value="2" selected="selected">2</option>
              <option value="3" selected="selected">3</option>
            </select>
          </div>
        </th>
        <th>
          <div class="periode">
            <label for="periode">Fin :</label>
            <select id="periode" name="periode">
              <option value="1" selected="selected">1</option>
              <option value="2" selected="selected">2</option>
              <option value="3" selected="selected">3</option>
            </select>
          </div>
        </th>
      </tr>
    </thead>
  </table>
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

        var options = { title: 'Chiffre d affaire en fonction de la zone géographique' };

        var chart = new google.visualization.BarChart(document.getElementById('graph2'));
        chart.draw(data, options);
      }
      google.charts.setOnLoadCallback(drawChart);
    </script>

    <table border="0">
      <thead>
        <tr>
          <th>
            <div class="periode">
              <label for="periode">Début :</label>
              <select id="periode" name="periode">
                <option value="1" selected="selected">1</option>
                <option value="2" selected="selected">2</option>
                <option value="3" selected="selected">3</option>
              </select>
            </div>
          </th>
          <th>
            <div class="periode">
              <label for="periode">Fin :</label>
              <select id="periode" name="periode">
                <option value="1" selected="selected">1</option>
                <option value="2" selected="selected">2</option>
                <option value="3" selected="selected">3</option>
              </select>
            </div>
          </th>
        </tr>
      </thead>
    </table>
    <div id="graph3" style="width: 550px; height: 400px; margin: 5px">
    </div>
    <script language="JavaScript">
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Client', 'Chiffre d affaire'],
          ['1', 900],
          ['2', 1000],
          ['3', 1170],

        ]);

        var options = { title: 'Chiffre d affaire en fonction du client' };

        var chart = new google.visualization.BarChart(document.getElementById('graph3'));
        chart.draw(data, options);
      }
      google.charts.setOnLoadCallback(drawChart);
    </script>


  </section>
</body>

</html>

