<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<c:url value="/static/datatables/css/jquery.dataTables.min.css"/>" >	 
<h2 class="sub-header">Autores</h2>

<a href="<c:url  value="/cadAutor"/>">
<button type="button" class="btn btn-default btn-md" >
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Novo
</button>
</a>

<c:if test="${not empty autores }" >
	<div class="table-responsive" style="margin-top:20px;">  
		<table id="tabautores" class="table table-striped"> 
			<thead>
				<tr>
					<!-- tive que tirar o bg image na m�o pois qnd carregava a primeira vez ele vinha mesmo n�o sendo orderable -->
					<th style="background-image:none;text-align:center;width:10px;">Alt</th>
					<th style="background-image:none;text-align:center;width:10px;">Exc</th>
					<th>Id</th>					
					<th>Nome</th>					
				</tr>
			</thead>
		
			<tbody>
			<c:forEach var="autor" items="${autores}">
				<tr>
					
					<td style="text-align:center; width:10px;" ><sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#autor)"> <a href="<c:url value="/editAutor/${autor.id}" />"><span title="Editar" class="glyphicon glyphicon-pencil" ></span></a> </sec:authorize> </td>
					<td style="text-align:center; width:10px;"> <sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#autor)"><a href="<c:url value="/delAutor/${autor.id}"/>" ><span title="Excluir" class="glyphicon glyphicon-remove" style="color:red"></span></a> </sec:authorize> </td>
					
					<td>${autor.id}</td>					
					<td style="width:90%">${autor.nome}</td>					
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
		var table =  $('#tabautores').DataTable({
			
			"language": {
					"url" : "/biblioteca/static/js/custom-brazilian-portuguese.json"
	        	},
	        	
	        	"lengthMenu": [5, 10, 20, 50],
	        	"pageLength": 5,
	        	 "columnDefs": [
			                       {
			                           "targets": [ 2 ],
			                           "visible": false,
			                           "searchable": true
			                       },
			                       {
			                           "targets": [ 0,1 ],
			                           "visible": true,
			                           "searchable": false,
			                           "orderable":false
			                       }
			                   ]
		});
		
		
		 
	    $('#tabautores tbody').on( 'click', 'tr', function () {
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
