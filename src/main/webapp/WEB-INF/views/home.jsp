<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>	
	
<tags:pageTemplate titulo="Livros de Java, SOA, Android, iPhone, Ruby on Rails e muito mais"> 
	
	<section id="index-section" class="container middle">
		
		<c:if test="${not empty message }">
			<h1 class="cdc-call">${message }</h1>
		</c:if>
		<c:if test="${empty message }">
			<h1 class="cdc-call">Últimos dias com os preços promocionais. Aproveite!</h1>
		</c:if>
		
		<ul class="clearfix book-collection">
			<c:forEach var="produto" items="${produtos }">
				<li>  
					<a href= "<c:url value="produtos/detalhe/${produto.id }" />" class="block clearfix">
						<img width="143" height="202" src="${produto.sumarioPath }" alt="${produto.titulo }" title="${produto.titulo }" /> 
						<h2 class="product-title"> ${produto.titulo } </h2> 
						<small class="buy-button">Compre</small>
					</a>
				</li>
			</c:forEach>
		</ul>

		<h2 class="cdc-call">Diferenciais da Casa do Código</h2>

		<ul id="cdc-diferenciais" class="clearfix">
			<li class="col-left">
				<h3>E-books sem DRM. Leia onde quiser</h3>
				<p><span class="sprite" id="sprite-drm"></span> Nossos e-books não possuem DRM, ou seja, você pode ler em qualquer computador, tablet e smartphone.</p>
			</li>
			<li class="col-right">
				<h3>Autores de renome na comunidade</h3>
				<p><span class="sprite" id="sprite-renome"></span> Autores que participam ativamente na comunidade com Open Source, listas de discussão, grupos emais.</p>
			</li>
			<li class="col-left">
				<h3>Receba atualizações dos e-books</h3>
				<p><span class="sprite" id="sprite-atualizacoes"></span> Quando você compra um e-book, automaticamente tem direito às atualizações e correções dele.</p>
			</li>
			<li class="col-right">
				<h3>Livros com curadoria da Caelum</h3>
				<p><span class="sprite" id="sprite-caelum"></span> Desenvolvedores experientes que avaliam e revisam os livros constantemente.</p>
			</li>
		</ul>
	</section>
	
</tags:pageTemplate>
	
	


