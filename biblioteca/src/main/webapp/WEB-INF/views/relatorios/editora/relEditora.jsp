<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="sub-header">Relatório Editoras</h2>
<div class="col-sm-12">
	<c:url value="/relatorios/editoras" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="filtros" target="_blank" action="${linkAct}"
		class="form-horizontal" role="form">
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Nome:</label>
			<div class="col-sm-6">
				<sf:input path="parteNome" type="text" class="form-control" id="parteNome"
					placeholder="Insira o nome ou parte dele para pesquisar" />
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
