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


</head>
<body> 
      
	<div class="container">
	
	<h2>Login Casa do Código</h2>
						
	<form:form servletRelativeAction="/login" method="post">
		<div class="form-group">
			<label>Email</label>
			<input name="username" type="text" class="form-control"/>
		</div>	 
		
		<div class="form-group">
			<label>Senha</label>
			<input name="password" type="password" class="form-control"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Entrar</button>
	</form:form>
	
	</div>
	
</body>
</html>