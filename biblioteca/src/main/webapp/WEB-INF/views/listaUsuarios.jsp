<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<link rel="stylesheet" href="<c:url value="/static/datatables/css/jquery.dataTables.min.css"/>" >	 
<h2 class="sub-header">Usu�rios</h2>

<a href="<c:url  value="/cadUsuario"/>">
<button type="button" class="btn btn-default btn-md" >
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Novo
</button>
</a>

<c:if test="${not empty usuarios }" >
	<div class="table-responsive" style="margin-top:20px;">  
		<table id="tabUsuarios" class="table table-striped"> 
			<thead>
				<tr>
					<!-- tive que tirar o bg image na m�o pois qnd carregava a primeira vez ele vinha mesmo n�o sendo orderable -->
					<th style="background-image:none;text-align:center">Alt</th>
					<th style="background-image:none;text-align:center">Exc</th>
					<th>Id</th>
					<th>Login</th>
					<th>Nome</th>					
				</tr>
			</thead>
		
			<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td style="text-align:center" > <a href="<c:url value="/editUsuario/${usuario.id}" />"><span title="Editar" class="glyphicon glyphicon-pencil" ></span></a> </td>
					<td style="text-align:center"> <a href="<c:url value="/delusuario/${usuario.id}"/>" ><span title="Excluir" class="glyphicon glyphicon-remove" style="color:red"></span></a> </td>
					<td>${usuario.id}</td>
					<td>${usuario.login}</td>
					<td style="width:70%">${usuario.nome}</td>					
				</tr>
			</c:forEach>
			</tbody>
		</table>
	 </div> 
</c:if>




<script type="text/javascript" src="<c:url value="/static/datatables/js/jquery.dataTables.min.js"/>"></script> 
<script type="text/javascript"> 
	$(document).ready(function() {
		var selected = [];
		var table =  $('#tabUsuarios').DataTable({
			
			"language": {
	            "lengthMenu": "Exibir _MENU_ registros",
	            "zeroRecords": "Nenhum registro encontrado",
	            "info": "P�gina _PAGE_ de _PAGES_",
	            "infoEmpty": "Nenhum registro encontrado",
	            "infoFiltered": "(filtrados de _MAX_ registros)",
	            "search":         "Pesquisar:",
	            "paginate": {
	                    "first":      "Primeira",
	                    "last":       "�ltima",
	                    "next":       "Pr�x",
	                    "previous":   "Ant"
	                }
	        	},
	        	
	        	"lengthMenu": [5, 10, 20, 50],
	        	"pageLength": 5,
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
		
		
		 
	    $('#tabUsuarios tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }	        
	    } );
	   
	} );
	
 </script> 
