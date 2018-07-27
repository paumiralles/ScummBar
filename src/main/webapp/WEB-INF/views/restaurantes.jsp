<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Restaurantes</title>
</head>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		

		<div class="body">
					<h1>Lista de Restaurantes</h1>
	<p>Estos son todos los resaurantes</p>
	<table class="table1">
		<thead >
			<tr class="table1">
				<th class="table1" width="10%">id</th>
				<th class="table1" width="15%">nombre</th>
				<th class="table1" width="10%">direccion</th>
				<th class="table1" width="10%">descripcion</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="restaurante" items="${restaurantes}">
				<tr>
				
					<td class="table1" >${restaurante.id}</td>
					<td class="table1" >${restaurante.nombre}</td>
					<td class="table1" >${restaurante.direccion}</td>
					<td class="table1" >${restaurante.descripcion}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
