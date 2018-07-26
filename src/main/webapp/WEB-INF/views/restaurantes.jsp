<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		

		<div class="body">
					<h1>Lista de Restaurantes</h1>
	<p>Estos son todos los resaurantes</p>
	<table>
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="15%">nombre</th>
				<th width="10%">direccion</th>
				<th width="10%">descripcion</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="restaurante" items="${restaurantes}">
				<tr>
				
					<td>${restaurante.id}</td>
					<td>${restaurante.nombre}</td>
					<td>${restaurante.direccion}</td>
					<td>${restaurante.descripcion}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
