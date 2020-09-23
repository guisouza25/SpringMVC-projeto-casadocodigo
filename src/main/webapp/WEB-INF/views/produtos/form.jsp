<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, PHP, Ruby e muito mais - Casa do Código</title>

<c:url value="/" var="contextPath" />
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">

<style type="text/css" >
	body {
	
	padding: 80px 0;
}

</style>

</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
  		<div class="container">
    		<div class="navbar-header">
    			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
    		 	</button>
     			<a class="navbar-brand" href='<c:url value="/" />' >Casa do Código</a>
   			</div>
   	
    		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
     			<ul class="nav navbar-nav">
			        <li><a href= '<c:url value="/produtos" />' >Lista de Produtos</a></li>
			        <li><a href= '<c:url value="/produtos/form" />' >Cadastro de Produtos</a></li>
       			</ul>
       			
       			<ul class= "nav navbar-nav navbar-right" >
       				<li><a href='<c:url value="/logout"/>' >Sair</a></li>
       			</ul>
       			
       			<ul class= "nav navbar-nav navbar-right" >
       				<li><a href="#">
       					<security:authentication property="principal" var="usuario"/>
       					Usuário: ${usuario.username }
       				</a></li>
       			</ul>
       			
      		</div>
      	</div>
     </nav> 
      
	<div class="container">
	
	<h2>Cadastro de Produto</h2>
	
	<br>
	<br>
						<!-- /casadocodigo/produtos -->
	<form:form servletRelativeAction="/produtos" method="post" modelAttribute="produto" enctype="multipart/form-data">
		
		<div class="form-group">
			<label>Título</label>
			<form:input path="titulo" cssClass="form-control"/>
			<form:errors path="titulo"/>
		</div>	  <!-- produto.titulo -->
		
		<div class="form-group">
			<label>Descrição</label>
			<form:textarea path="descricao" cssClass="form-control"/>
			<form:errors path="descricao"/>
		</div>
		
		<div class="form-group">
			<label>Páginas</label>
			<form:input path="paginas" cssClass="form-control"/>
			<form:errors path="paginas"/>
		</div>
		
		<div class="form-group">
			<label>Data Lançamento</label>
			<form:input path="dataLancamento" placeHolder="dd/mm/aaaa" cssClass="form-control"/>
			<form:errors path="dataLancamento"/>
		</div>
		
		<c:forEach var="t" items="${tipos}" varStatus="status">
		<div class="form-group">
			<label>Preço ${t}</label>
			<form:input path="precos[${status.index}].valor" cssClass="form-control"/>
			<form:input type="hidden" path="precos[${status.index}].tipo" value="${t}" />
		</div>
		</c:forEach>
		
		<div class="form-group">
			<label>Sumário</label>
			<input name="sumario" type="file" class="form-control"> 
		</div>
		
		<button type="submit" class="btn btn-primary">Cadastrar</button>
	</form:form>
	
	</div>
	
</body>
</html>