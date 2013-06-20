<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<title>Creation Produit</title>
</head>
<body>
	
	
	<label for="nomUtilisateur">Nom de l'utilisateur</label>
	<input type="text" id="nomUtilisateur" name="nomUtilisateur"
		value="${sessionScope.sessionUtilisateur.loginUser}" size="20" maxlength="20" readonly="true" />
	<span class="erreur"></span>
	<br />
	
	<label for="nomProduit">Nom du produit<span class="requis">*</span></label>
	<input type="text" id="nomProduit" name="nomProduit"
		value="" size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />

	<label for="garantieProduit">Durée de garantie </label>
	<input type="text" id="garantieProduit" name="garantieProduit"
		value="" size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />

	<label for="debutValiditePrix">Debut de validitée du prix <span class="requis">*</span></label>
	<input type="text" id="debutValiditePrix" name="debutValiditePrix"
		value="" size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />
	
	<label for="finValiditePrix">Fin de validitée du prix <span class="requis">*</span></label>
	<input type="text" id="finValiditePrix" name="finValiditePrix"
		value="" size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />
	
	<label for="valeurPrix">Prix <span class="requis">*</span></label>
	<input type="text" id="valeurPrix" name="valeurPrix"
		value=""size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />
	
	<label for="qteProduitStocke">Quantité <span class="requis">*</span></label>
	<input type="text" id="qteProduitStocke" name="qteProduitStocke"
		value="" size="20" maxlength="20" />
	<span class="erreur"></span>
	<br />
	<br/>
	
	
 
	Fournisseur :  <select id="listeDeroulante" name="listeDeroulante">
	
	 <c:forEach items="${liste}" var="liste">
       		<option   > ${liste.nomFournisseur } </option>

    </c:forEach>
					
						
			</select>		
		</br>
		</br>		

	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
</body>
</html>