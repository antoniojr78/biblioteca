<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2 class="sub-header">Edição de Autor</h2>
<div class="col-sm-6">
	<c:url value="/alterarAutor" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form commandName="autor" modelAttribute="autor" action="${linkAct}"
		class="form-horizontal" role="form" >
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Id:</label>
			<div class="col-sm-10">
				<sf:input path="id"  readonly="true" type="text" class="form-control"
					id="inputId"  />
			</div>
		</div>			
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Nome:</label>
			<div class="col-sm-10">
				<sf:input path="nome" type="text" class="form-control" id="nome"
					placeholder="Nome" readonly="${!podeAlterar}"  />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default" ${podeAlterar == true?'':'disabled'}  >
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Salvar
				</button> 
				<a href="<c:url  value="/listaAutores"/>">
					<button type="button" class="btn btn-default btn-md">
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> Voltar
					</button>
				</a>
			</div>

		</div>
	</sf:form>
</div>
