<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2 class="sub-header">Cadastro Parâmetros</h2>
<div class="col-sm-6">
	<c:url value="${springBase}/salvarParametro" var="linkAct" />

	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="parametrosCmd" action="${linkAct}"
		class="form-horizontal" role="form">
		
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
		<c:if test="${not empty parametrosCmd.parametros }">
			<div class="table-responsive" style="margin-top:20px;">  
		<table id="tabParms" class="table table-striped">
			<thead>
				<th>Cód</th>					
				<th>Descrição</th>			
				<th>Valor</th>
			</thead>
			<c:forEach items="${parametrosCmd.parametros }" varStatus="vs" var="p">
				
				<tr>
					<sf:hidden path="parametros[${vs.index }].id"/>
					<td><sf:input path="parametros[${vs.index }].codParam" readonly="true"/> </td>
					<td><sf:input path="parametros[${vs.index }].nomeParam" readonly="true"/> </td>
					<td><sf:input path="parametros[${vs.index }].valorParam"/> </td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Salvar
				</button>
			</div>

		</div>
	</sf:form>
</div>
