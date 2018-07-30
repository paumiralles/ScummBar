<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.7.3/themes/base/jquery-ui.
css">
<script>
	$(function() {
		$('#dia').datepicker({
			dateFormat : 'mm/dd/yy'
		});
		$('#dia').attr('readonly', true);
	});
</script>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hacer una reserva</title>
</head>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">


		<div class="body">
			
			<h1>Reservar</h1>
			<p>Aquí puedes hacer una reserva</p>
			<form:form method="POST" action="reservar">
				<div>
					<label for="id">Restaurante</label>
					<form:select id="id" path="restauranteId"
						items="${command.restaurantes}" itemValue="id" itemLabel="nombre"
						htmlEscape="true" />

				</div>
				<div>
					<label for="dia">Día</label>
					<form:input path="dia" type="text" cssClass="date-picker" />
				</div>
				<div>
					<label for="turno">Turno</label>
					<form:select path="turnoId" items="${command.turnos}"
						itemValue="id" itemLabel="descripcion" htmlEscape="true" />
				</div>
				<div>
					<label for="personas">Personas</label>
					<form:select path="personas" htmlEscape="true">
						<c:forEach begin="1" end="10" var="count">
							<form:option value="${count}" />
						</c:forEach>
					</form:select>
				</div>
				<div>
					<button type="submit" value="Reservar">Reservar</button>
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

