<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript"> 
	$(document).ready(function() {
		var selected = [];
		var table =  $('#example').DataTable({
			
			"language": {
	            "lengthMenu": "Exibir _MENU_ registros",
	            "zeroRecords": "Nenhum registro encontrado",
	            "info": "Página _PAGE_ de _PAGES_",
	            "infoEmpty": "Nenhum registro encontrado",
	            "infoFiltered": "(filtered from _MAX_ total records)",
	            "search":         "Pesquisar:",
	            "paginate": {
	                    "first":      "Primeira",
	                    "last":       "Última",
	                    "next":       "Próx",
	                    "previous":   "Ant"
	                }
	        	},
	        	"lengthMenu": [5, 10, 20, 50],
	        	 "columnDefs": [
			                       {
			                           "targets": [ 2 ],
			                           "visible": false,
			                           "searchable": false
			                       },
			                       {
			                           "targets": [ 0,1 ],
			                           "visible": true,
			                           "searchable": false,
			                           "orderable":false
			                       }
			                   ]
		});
	
	
		
		 
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }	        
	    } );
		
	} );
	
	function prox(){
		
		var offset = parseInt($("#offset").val()) + 5 ;
		 $("#offset").val(offset); 
	}
 </script> 
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.5/css/jquery.dataTables.css" >
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script> 
	<!-- 
		<link rel="stylesheet" href="<c:url  value="/static/datatables/css/jquery.dataTables.css"/>" >
	<script type="text/javascript" src="<c:url  value="/static/datatables/js/jquery.dataTables.min.js"/>"></script> 
	
	 -->
<h2 class="sub-header">Usuários</h2>

<a href="<c:url  value="/cadUsuario"/>">
<button type="button" class="btn btn-default btn-md" >
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Novo
</button>
</a>

<c:if test="${not empty usuarios }" >
	<div class="table-responsive" style="margin-top:20px;"><!--  -->
		<table id="example" class="table table-striped"> <!-- class="table table-striped" -->
			<thead>
				<tr>
					<!-- tive que tirar o bg image na mão pois qnd carregava a primeira vez ele vinha mesmo não sendo orderable -->
					<th colspan="2" style="background-image:none;text-align:center">&nbsp</th>
					<th>Id</th>
					<th>Login</th>
					<th>Nome</th>					
				</tr>
			</thead>
			<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td style="text-align:center"> <a href="<c:url value="/delusuario/${usuario.id}"/>" ><span title="Excluir" class="glyphicon glyphicon-remove" style="color:red"></span></a> </td>
					<td style="text-align:center" > <a href="<c:url value="/editUsuario/${usuario.id}" />"><span title="Editar" class="glyphicon glyphicon-pencil" ></span></a> </td>
					<td>${usuario.id}</td>
					<td>${usuario.login}</td>
					<td style="width:70%">${usuario.nome}</td>					
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
</c:if>