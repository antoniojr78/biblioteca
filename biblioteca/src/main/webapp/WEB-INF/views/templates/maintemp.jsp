<!DOCTYPE html>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link rel="stylesheet" href="<c:url value="/recursos/stylesheets/foundation.min.css"/>" />
	<link rel="stylesheet" href="<c:url value="/recursos/stylesheets/estilo.css"/>" />
	<title><tiles:getAsString name="titulo" /></title>
	<script type="text/javascript" src="<c:url value="/recursos/javascripts/jquery.js"/>"></script>
</head>
<body>
	
	

	
	Aqui é main<br>
	<tiles:insertAttribute name="conteudo"/>
	<br>
	aqui e main denovo


</body>
</html>