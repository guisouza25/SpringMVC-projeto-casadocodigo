<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>	
	
<tags:pageTemplate titulo="Produto não encontrado"> 

	<section id="index-section" class="container middle">
		<h2>${msg }</h2>
		<!-- 
			Mensagem: ${exception.message}
			<c:forEach var="stack" items="${exception.stackTrace}">
				${stack}
			</c:forEach>
		 -->
	</section>
	
</tags:pageTemplate>
	
	


