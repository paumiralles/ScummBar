<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<div class="alert alert-block alert-success">
				<c:choose>
					<c:when test="${cancelado}">Reserva cancelada</c:when>
					<c:otherwise>No se ha encontrado el localizador</c:otherwise>
				</c:choose>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>