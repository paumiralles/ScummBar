<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.7.3/themes/base/jquery-ui.css">
<script>
	$(function() {
		$( '#dia' ).datepicker({ dateFormat: 'dd/mm/yy'});
		$( '#dia' ).attr('readonly', true);
	});
</script>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<form:form action="cancelar" method="post">
				<div>
					<label for="restaurante">Restaurante</label>
					<form:select path="restauranteId" items="${command.restaurantes}"
						itemValue="id" itemLabel="nombre" htmlEscape="true" />
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
					<label for="localizador">Localizador</label>
					<form:input path="localizador" type="text" />
				</div>
				<div>
					<button type="submit" value="Cancelar">Cancelar</button>
				</div>
			</form:form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>