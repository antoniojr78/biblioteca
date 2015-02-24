<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
		<div class="form-group">
			<label class="control-label col-sm-2" for="pwd">Senha:</label>
			<div class="col-sm-10">
				<sf:input path="senha" type="password" class="form-control" id="pwd"
					placeholder="Senha" maxlength="10" />					
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Salvar</button>
			</div>
		</div>
	</sf:form>
</div>
