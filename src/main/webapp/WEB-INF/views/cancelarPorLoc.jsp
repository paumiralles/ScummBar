<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.7.3/themes/base/jquery-ui.
css">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hacer una cancelación con el localizador</title>
</head>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<div class="body">
		
			<h1>Cancelar</h1>
			<p>Aquí puedes hacer una cancelación con el localizador de tu
				reserva</p>
			<form:form method="POST" action="cancelarLoc">
				<div>
					<label for="localizador">Localizador</label>
					<form:input path="localizador"/>
				</div>
				<div>
					<button type="submit" value="CancelarLoc">Cancelar</button>
				</div>
			</form:form>
			</div>
			
		
	</tiles:putAttribute>
</tiles:insertDefinition>
