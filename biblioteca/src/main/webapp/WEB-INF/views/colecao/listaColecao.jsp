<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<c:url value="/static/datatables/css/jquery.dataTables.min.css"/>" >	 
<h2 class="sub-header">Coleções</h2>

<a href="<c:url  value="/formColecao"/>">
<button type="button" class="btn btn-default btn-md" >
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Novo
</button>
</a>

<c:if test="${not empty colecoes }" >
	<div class="table-responsive" style="margin-top:20px;">  
		<table id="tabcolecoes" class="table table-striped"> 
			<thead>
				<tr>
					<!-- tive que tirar o bg image na mão pois qnd carregava a primeira vez ele vinha mesmo não sendo orderable -->
					<th style="background-image:none;text-align:center;width:10px;">Alt</th>
					<th  style="background-image:none;text-align:center;width:10px;">Exc</th>
					<th>Id</th>					
					<th>Nome</th>					
				</tr>
			</thead>
		
			<tbody>
			<c:forEach var="colecao" items="${colecoes}">
				<tr>
					<!-- /delColecao/${colecao.id} -->
					<td style="text-align:center; width:10px;" ><sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#colecao)"> <a href="<c:url value="/editColecao/${colecao.id}" />"><span title="Editar" class="glyphicon glyphicon-pencil" ></span></a> </sec:authorize> </td>
					<td  style="text-align:center; width:10px;"> <sec:authorize access="@AlteracaoRegistroSecurityService.podeAlterar(#colecao)"><a href="<c:url value="#"/>" ><span title="Excluir" id="icon-delete" class="glyphicon glyphicon-remove" style="color:red"></span></a> </sec:authorize> </td>
					
					<td>${colecao.id}</td>					
					<td style="width:90%">${colecao.nome}</td>					
				</tr>
				
			</c:forEach>
			</tbody>
		</table>
	 </div> 
</c:if>




<script type="text/javascript" src="<c:url value="/static/datatables/js/jquery.dataTables.min.js"/>"></script> 
<script type="text/javascript"> 

	$(document).ready(function() {
		function pegaValorId(selector,index){
			
			$("#"+selector+" tbody tr").click(function() { alert('cliquei');
			
				var column_data = oTable.row(this).data()[index];
				alert(column_data);
			});
		}
		var selected = [];
		
		$('#tabcolecoes tbody tr').click(function () {
			
            var aPos =$(this).closest("tr").get(0);
            alert(aPos);
        });
		var table =  $('#tabcolecoes').DataTable({
			
			"language": {
					"url" : "/biblioteca/static/js/custom-brazilian-portuguese.json"
	        	},
	        	
	        	"lengthMenu": [5, 10, 20, 50],
	        	"pageLength": 5,
	        	"fnDrawCallback": pegaValorId('#tabcolecoes',0),
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
	    
		
		 /*
	    $('#tabcolecoes tbody').on( 'click', 'tr', function () {
	    	 var row = table.row( $(this).parents('tr') );
	    	 
	    	alert('teste');
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }	        
	    } ); */
	    
	} );
	
 </script> 
