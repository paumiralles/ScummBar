<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" isELIgnored="false"%>
<div class="menu">
	<spring:url value="/" var="home"
				htmlEscape="true" /> <a href="${home}">Menu</a>
				
				
				
				
	<table class="table2">
		<tbody>
			<tr class="table2"><td class="table2"><spring:url value="/restaurantes" var="homeUrl"
				htmlEscape="true" /> <a href="${homeUrl}">Restaurantes</a></td></tr>
			<tr class="table2"><td class="table2"><spring:url value="/reservar" var="bookingUrl"
				htmlEscape="true" /> <a href="${bookingUrl}">Realizar una reserva</a></td></tr>
<%-- 			<tr class="table2"><td class="table2"><spring:url value="/cancelar" var="cancelUrl" --%>
<%-- 				htmlEscape="true" /> <a href="${cancelUrl}">Cancelar una reserva</a></td></tr> --%>
			<tr class="table2"><td class="table2"><spring:url value="/cancelarLoc" var="cancelUrlLoc"
				htmlEscape="true" /> <a href="${cancelUrlLoc}">Cancelar una reserva con localizador</a></td></tr>
						
		</tbody>
	</table>	
	
	
	
	
			
<!-- 	<ul> -->
<%-- 		<li><spring:url value="/restaurantes" var="homeUrl" --%>
<%-- 				htmlEscape="true" /> <a href="${homeUrl}">Restaurantes</a></li> --%>
<%-- 		<li><spring:url value="/reservar" var="bookingUrl" --%>
<%-- 				htmlEscape="true" /> <a href="${bookingUrl}">Realizar una reserva</a></li> --%>
<%-- 		<li><spring:url value="/cancelar" var="cancelUrl" --%>
<%-- 				htmlEscape="true" /> <a href="${cancelUrl}">Cancelar una reserva</a> --%>
<!-- 		</li> -->
<%-- 		<li><spring:url value="/cancelarLoc" var="cancelUrlLoc" --%>
<%-- 				htmlEscape="true" /> <a href="${cancelUrlLoc}">Cancelar una reserva con localizador</a> --%>
<!-- 		</li> -->
<!-- 	</ul> -->
</div>