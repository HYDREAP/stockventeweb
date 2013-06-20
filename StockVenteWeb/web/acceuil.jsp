<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<c:import url="/inc/menu.jsp"></c:import>
</head>
<body>

	 <%-- Appel de l'objet Utilisateur en session avec l'objet implicite sessionScope --%>
                    <p class="succes">Bonjours: ${sessionScope.sessionUtilisateur.loginUser}</p></br>
                    <p> ${sessionScope.sessionUtilisateur.idUser } </br>
                     ${utilisateur.nomUser }</p>
               

</body>
</html>