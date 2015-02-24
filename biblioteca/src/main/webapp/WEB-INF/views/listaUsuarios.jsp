<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript"> 
	$(document).ready(function() {
		$('#example').DataTable();
	} );
	
	function prox(){
		
		var offset = parseInt($("#offset").val()) + 5 ;
		 $("#offset").val(offset); 
	}
 </script> 
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.5/css/jquery.dataTables.css" >
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script> 
	
<h2 class="sub-header">Lista Usuários</h2>
<c:if test="${not empty usuarios }" >
	<div ><!-- class="table-responsive" -->
		<table id="example" class="table table-striped"> <!-- class="table table-striped" -->
			<thead>
				<tr>
					<th>Id</th>
					<th>Login</th>
					<th>Nome</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.login}</td>
					<td style="width:70%">${usuario.nome}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
</c:if>