<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
                <title>PAGE D'ACCUEIL</title>
                <link rel="stylesheet" media="screen" href="v2.css" type="text/css"/>
	</head>
	<body>
		
                <section id="accueil">
                    <div class="log-form">
                    <h2>Gestion de commande - Accueil</h2>
		
		<div style="color:red">${errorMessage}</div>

		<form action="<c:url value="/" />" method="POST"> <!-- l'action par défaut est l'URL courant, qui va rappeler la servlet -->
			login (admin@admin.com) :<input type="text" title="Username" placeholder="Identifiant" name='loginParam' /> <br>
			password (admin): <input type="password" title="username" placeholder="Mot de passe" name='passwordParam' /> <br>
			<!--<input type='submit' name='action' value='login'>-->
                        <button type="submit" class="btn" name='action' value='login'>Connexion</button>
		</form>
		<!-- On montre le nombre d'utilisateurs connectés -->
		<!-- Cette information est stockée dans le scope "application" par le listener -->
		<h3>Il y a actuellement ${applicationScope.numberConnected} utilisateurs connectés</h3>
                    </div>
                </section>
	</body>
</html>
