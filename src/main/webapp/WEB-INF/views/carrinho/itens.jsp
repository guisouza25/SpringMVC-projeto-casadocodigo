<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
	
<tags:pageTemplate titulo="Seu Carrinho de Compras">	
	
	<jsp:attribute name="extraScripts">
		<script> 
			console.log("Finalização de compra de ${carrinhoCompras.quantidade} itens"); 
			if('${sucesso}' != '') {
				alert('${sucesso}');
			}
			if('${erroPagamento }' != '') {
				alert('${erroPagamento }')
			}
		</script>
		
		
	</jsp:attribute>
	
	<jsp:body>
	
	
	<section class="container middle">
		<h2 id="cart-title">Seu carrinho de compras</h2>
		<table id="cart-table">
			<colgroup>
				<col class="item-col" />
				<col class="item-price-col" />
				<col class="item-quantity-col" />
				<col class="line-price-col" />
				<col class="delete-col" />
			</colgroup>
			<thead>
				<tr>
					<th class="cart-img-col"></th>
					<th width="65%">Item</th>
					<th width="10%">Preço</th>
					<th width="10%">Quantidade</th>
					<th width="10%">Total</th>
					<th width="5%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${carrinhoCompras.itens }" >
					<tr>
						<td class="cart-img-col"><img src="${item.produto.sumarioPath }" width="71px" height="100px" /></td>
						<td class="item-title"> <a href= <c:url value="/produtos/detalhe/${item.produto.id }" /> >${item.produto.titulo }</a> </td>
						<td class="numeric-cell">${item.preco }</td>
						<td class="quantity-input-cell"><input type="number" min="0" id="quantidade" name="quantidade" value="${carrinhoCompras.getQuantidade(item) }"/> </td>
						<td class="numeric-cell"> ${carrinhoCompras.getTotal(item) } </td>
						<td class="remove-item">
							<form:form servletRelativeAction="/carrinho/remover/${item.produto.id}/${item.tipoPreco }" method="POST">
								<input type="image" src='<c:url value="/resources/imagens/excluir.png" />' alt="Excluir" title="Excluir" />
							</form:form>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
			
			<tfoot>
				<tr>
					<td colspan="4">
						<form:form servletRelativeAction="/pagamento/cadastro" method="post"> 
							<input type="submit" class="checkout" name="checkout" value="Finalizar compra" />
						</form:form>
					</td>
					<td class="numeric-cell"> ${carrinhoCompras.total} </td>
					<td></td>
				</tr>
			</tfoot>
		</table>

		<h2><a href= '<c:url value="/" />'>Veja todos os livros que publicamos!</a></h2>
	</section>
	</jsp:body>
	
</tags:pageTemplate>

	
