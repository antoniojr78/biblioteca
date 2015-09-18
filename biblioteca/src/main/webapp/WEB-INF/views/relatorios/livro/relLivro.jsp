<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="sub-header">Relatório Livros</h2>
<div class="col-sm-12">
	<c:url value="/relatorios/livros" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="filtros" target="_blank" action="${linkAct}"
		class="form-horizontal" role="form">
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="parteTitulo">Título:</label>
			<div class="col-sm-6">
				<sf:input path="parteNome" type="text" class="form-control" id="parteTitulo"
					placeholder="Insira o título ou parte dele para pesquisar" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="parteISBN">ISBN:</label>
			<div class="col-sm-6">
				<sf:input path="parteISBN" type="text" class="form-control" id="parteISBN"
					placeholder="Insira o ISBN ou parte dele para pesquisar" />
			</div>
		</div>		
		<div class="form-group">
			<label class="control-label col-sm-2" for="parteAutor">Autor:</label>
			<div class="col-sm-6">
				<sf:input path="parteAutor" type="text" class="form-control" id="parteAutor"
					placeholder="Insira o nome do autor ou parte dele para pesquisar" />
			</div>
		</div>	
		<div class="form-group">
			<label class="control-label col-sm-2" for="parteEditora">Editora:</label>
			<div class="col-sm-6">
				<sf:input path="parteEditora" type="text" class="form-control" id="parteEditora"
					placeholder="Insira o nome da editora ou parte dele para pesquisar" />
			</div>
		</div>	
		<div class="form-group">
			<label class="control-label col-sm-2" for="parteColecao">Coleção:</label>
			<div class="col-sm-6">
				<sf:input path="parteColecao" type="text" class="form-control" id="parteColecao"
					placeholder="Insira o nome da coleção ou parte dele para pesquisar" />
			</div>
		</div>							
		<div class="form-group">
			<label class="control-label col-sm-2" for="formatos">Formatos:</label>
			<div class="col-sm-6">
				<sf:radiobuttons path="formato" items="${filtros.formatos}" id="formatos" element="div" cssClass="radioFomatos"/>
			</div>
		</div>		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<!-- onclick="exibirRelatorio('${linkAct}',parteNome.value)" -->
				<button type="submit" class="btn btn-default"  >
					<span class="glyphicon glyphicon-print" aria-hidden="true"></span>&nbsp;Imprimir
				</button>
			</div>
		</div>
	</sf:form>
</div>
