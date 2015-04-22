<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="sub-header">Cadastro de Livros</h2>
<div class="col-sm-12">
	<c:url value="/salvarLivro" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="livro" action="${linkAct}"
		class="form-horizontal" role="form">
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputISBN">ISBN:</label>
			<div class="col-sm-6">

				<sf:input path="isbn" type="text" class="form-control"
					id="inputISBN" placeholder="ISBN" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputTitulo">Título:</label>
			<div class="col-sm-6">

				<sf:input path="titulo" type="text" class="form-control"
					id="inputTitulo" placeholder="Título" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputAutor">Autor:</label>
			<div class="col-sm-6">
				<sf:select path="autor" class="form-control" id="inputAutor">
					<sf:option Label="Selecione" value="" />
					<sf:options items="${listaAutores}" itemValue="id" itemLabel="nome" />
				</sf:select>

			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputEditora">Editora:</label>
			<div class="col-sm-6">
				<sf:select path="editora" class="form-control" id="inputEditora">
					<sf:option Label="Selecione" value="" />
					<sf:options items="${listaEditoras}" itemValue="id"
						itemLabel="nome" />
				</sf:select>

			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputColecao">Coleção:</label>
			<div class="col-sm-6">
				<sf:select path="colecao" class="form-control" id="inputColecao">
					<sf:option Label="Selecione" value="" />
					<sf:options items="${listaColecoes}" itemValue="id"
						itemLabel="nome" />
				</sf:select>

			</div>
		</div>


		<div class="form-group">
			<label class="control-label col-sm-2" for="inputEmprestado">Emprestado?</label>
			<div class="col-sm-6">
				<sf:checkbox path="emprestado" id="inputEmprestado"
					/>
			</div>

		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputListaDesejo">Lista Desejo?</label>
			<div class="col-sm-6">
				<sf:checkbox path="listaDesejo" id="inputListaDesejo"/>
			</div>

		</div>
		<!-- Bootstrap Switch -->
		<link href="<c:url value="/static/bootstrap/css/bootstrap-switch.min.css"/>" rel="stylesheet">
		<script src="<c:url value="/static/bootstrap/js/bootstrap-switch.min.js"/>"></script>
		<script>
			$("#inputEmprestado").bootstrapSwitch({"onText":"Sim","offText":"Não"});
			$("#inputListaDesejo").bootstrapSwitch({"onText":"Sim","offText":"Não"});
		</script>
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputSinopse">Sinopse:</label>
			<div class="col-sm-8">
				<sf:textarea path="sinopse" id="inputSinopse" />
				
			</div>

		</div>
		
		
		<script src="<c:url value="/static/ckeditor/ckeditor.js"/>"></script>
			
		<script type="text/javascript">
			CKEDITOR.replace( 'inputSinopse' );
		</script>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Salvar
				</button>
				<a href="<c:url  value="/listaLivro"/>">
					<button type="button" class="btn btn-default btn-md">
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
						Voltar
					</button>
				</a>
			</div>

		</div>
	</sf:form>
</div>
