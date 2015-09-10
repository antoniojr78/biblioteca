<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2 class="sub-header">Cadastro de Usuários</h2>
<div class="col-sm-6">
	<c:url value="/salvarUsuario" var="linkAct" />
	
	<div class="alert alert-info"
		style='display:${msg==null?"none":"line"}' role="alert">${msg }
	</div>

	<sf:form modelAttribute="usuario" action="${linkAct}"
		class="form-horizontal" role="form">
		<sf:errors path="*" element="div" role="alert"
			cssClass="alert alert-danger" />
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Email:</label>
			<div class="col-sm-10">
				<sf:input path="login" type="text" class="form-control"
					id="inputEmail" placeholder="E-mail" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="email">Nome:</label>
			<div class="col-sm-10">
				<sf:input path="nome" type="text" class="form-control" id="email"
					placeholder="Nome" />
			</div>
		</div>
		<sec:authorize access="@AlteracaoRegistroSecurityService.isMaster(#usuario)">
		<div class="form-group">
			<label class="control-label col-sm-2" for="master">Master?</label>
			<div class="col-sm-10">
				<sf:checkbox path="master"  id="inputMaster"  />
			</div>
		</div>	
		</sec:authorize>	
		<div class="form-group">
			<label class="control-label col-sm-2" for="pwd">Senha:</label>
			<div class="col-sm-10">
				<sf:input path="senha" type="password" class="form-control" id="pwd"
					placeholder="Senha" maxlength="10" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Salvar
				</button>
				<a href="<c:url  value="/listaUsuario"/>">
					<button type="button" class="btn btn-default btn-md">
						<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> Voltar
					</button>
				</a>
			</div>

		</div>
		<!-- Bootstrap Switch -->
		<link href="<c:url value="/static/bootstrap/css/bootstrap-switch.min.css"/>" rel="stylesheet">
		<script src="<c:url value="/static/bootstrap/js/bootstrap-switch.min.js"/>"></script>
		<script>
			$("#inputMaster").bootstrapSwitch({"onText":"Sim","offText":"Não"});		
		</script>
	</sf:form>
	
</div>
