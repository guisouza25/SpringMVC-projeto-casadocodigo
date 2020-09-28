<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, PHP, Ruby e muito mais - Casa do Código</title>

<c:url value="/" var="contextPath" />
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap.min.css" >
<link rel="stylesheet" href="${contextPath}resources/css/bootstrap-theme.min.css">

<style type="text/css">
	body {
	padding-top: 80px;
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
     			<a class="navbar-brand" href= '<c:url value="/" />' >Casa do Código</a>
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
	
	<div class="container">
		<h2>Lista de Produtos</h2>
		<h3 class="text-center" >${msg }</h3>
	</div>
	
	<table class="table table-bordered table-hover"  >
		<tr>
			<th class="text-center">Título</th>
			<th class="text-center">Descrição</th>
			<th class="text-center">Precos</th>
			<th class="text-center">Páginas</th>
		</tr>
		<c:forEach var="produto" items="${produtos}" varStatus="status">
			<tr>
				<td class="text-center">
					<a href= "<c:url value="produtos/detalhe/${produto.id }" />" >${produto.titulo}  </a><br><br>
					<a href= "<c:url value="produtos/editar/${produto.id }" />" >editar</a>
				</td>
				<td>${produto.descricao}</td>
				<td>
					<c:forEach var="preco" items="${produto.precos}">
						${preco.tipo} - ${preco.valor}
					</c:forEach>
				</td>
				<td class="text-center" >${produto.paginas}</td>
			</tr>	
		</c:forEach>
	</table>
	
	</div>
	
</body>
</html>