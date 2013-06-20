<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<div>
	
    <center><img src="images/livraison_image.jpg" alt="image livraison" height=150 width= 60% /></center>
 
	<fieldset>
	<legend>Menu</legend>
	
		
		<li><a href="creationClient.jsp" > Créer un client</a><br/></li>
		<li><a href="creationProduit" > Créer un produit</a><br/></li>
		<li><a href="<c:url value="" />"> Modifier/Supprimer un client</a><br/></li>	
		<li><a href="rechercheProduit.jsp"> Rechercher produits</a><br/></li>
		<li> <a href="rechercheClient.jsp">Rechercher un client</a><br/></li>
		<li><a href="<c:url value="" />"> Rechercher facture</a><br/></li>
		<li><a href="<c:url value="" />"> Vendre produits</a><br/></li>
		
		
	</fieldset>

</div>
</body>
</html>