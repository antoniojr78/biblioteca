<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<c:url value="/static/datatables/css/jquery.dataTables.min.css"/>" >	 
<h2 class="sub-header">Livros</h2>

<a href="<c:url  value="/cadLivro"/>">
<button type="button" class="btn btn-default btn-md" >
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Novo
</button>
</a>

<c:if test="${not empty livros }" >
	<div class="table-responsive" style="margin-top:20px;">  
		<table id="tabLivros" class="table table-striped"> 
			<thead>
				<tr>
					<!-- tive que tirar o bg image na mão pois qnd carregava a primeira vez ele vinha mesmo não sendo orderable -->
					<th style="background-image:none;text-align:center;width:10px;">Alt</th>
					<th  style="background-image:none;text-align:center;width:10px;">Exc</th>
					<th>Id</th>					
					<th>ISBN</th>					
				</tr>
			</thead>
		
			<tbody>
			<c:forEach var="livro" items="${livros}">
				<tr>
					
					<td style="text-align:center; width:10px;" ><sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#livro)"> <a href="<c:url value="/editLivro/${livro.id}" />"><span title="Editar" class="glyphicon glyphicon-pencil" ></span></a> </sec:authorize> </td>
					<td  style="text-align:center; width:10px;"> <sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#livro)"><span title="Excluir" id="icon-delete" class="glyphicon glyphicon-remove" style="color:red"></span> </sec:authorize> </td>
					
					<td>${livro.id}</td>					
					<td style="width:90%">${livro.isbn}</td>					
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
		

		var table =  $('#tabLivros').DataTable({
			
			"language": {
					"url" : "/biblioteca/static/js/custom-brazilian-portuguese.json"
	        	},
	        	
	        	"lengthMenu": [5, 10, 20, 50],
	        	"pageLength": 5,
	        	//"fnDrawCallback": removePorId(2,table,'url'),
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
	    
    	///////////
    	
    	$("table tbody tr #icon-delete").click(function() { 		
			removePorId(2,table,'delLivro',this);
		});
		    	
    	////////////
    	
	} );
	
 </script> 
