<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="sub-header">Cadastro de Livros</h2>
<div class="col-sm-6">
	<c:url value="/salvarLivro" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="livro" action="${linkAct}"
		class="form-horizontal" role="form">
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label  class="control-label col-sm-2" for="inputISBN">ISBN:</label>
			<div class="col-sm-10">
			
				<sf:input path="isbn" type="text" class="form-control"
					id="inputISBN" placeholder="ISBN"  />
			</div>
		</div>
		<div class="form-group">
			<label  class="control-label col-sm-2" for="inputTitulo">Título:</label>
			<div class="col-sm-10">
			
				<sf:input path="titulo" type="text" class="form-control"
					id="inputTitulo" placeholder="Título"  />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Salvar
				</button>
				<a href="<c:url  value="/listaColecao"/>">
					<button type="button" class="btn btn-default btn-md">
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> Voltar
					</button>
				</a>
			</div>

		</div>
	</sf:form>
</div>
