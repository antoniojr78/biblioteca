var globalAlertId = "globalAlert"
/*function removePorId(index,obj,url,elmsg){
	
	$("table tbody tr #icon-delete").click(function() { 		
		var linha = this.closest('tr');
		var id = obj.row(linha).data()[index];
		
		//Chamar a rotina de deleção via ajax
		$.post(url,{'id':id})
		
		.done(function(data,status){
			if(data=="OK") {
				linha.remove();				
				hideGlobalMsg();
			}else{
				
				//msg="Ocorreu um erro ao deletar o registro. O registro possui relação em outra parte do sistema";
				showGlobalMsg('erro',data);
			}

		})
		.error(function(xhr, textStatus, errorThrown){
			var msg="Ocorreu um erro ao deletar o registro"
			
			showGlobalMsg('erro',msg);
	
		});
	});
}

*/
	function removePorId(index,table,url,elem){
	
	
		bootbox.confirm("Deseja realmente apagar o registro?", function(result) {
		
			
			
			if(!result)return true;
			
			var linha = elem.closest('tr');
			var id = table.row(linha).data()[index];
		
			//Chamar a rotina de deleção via ajax
			$.post(url,{'id':id})
			
			.done(function(data,status){
				if(data=="OK") {
					linha.remove();	
					table.row(linha).remove().draw();
					hideGlobalMsg();
					
				}else{
					
					//msg="Ocorreu um erro ao deletar o registro. O registro possui relação em outra parte do sistema";
					showGlobalMsg('erro',data);
				}
	
			})
			.error(function(xhr, textStatus, errorThrown){
				var msg="Ocorreu um erro ao deletar o registro"
				
				showGlobalMsg('erro',msg);
		
			});
		});
	}
	
function hideGlobalMsg(){
	$(globalAlert).addClass("Alertoculto");
}
function showGlobalMsg(tipo,msg){
	globalAlert = document.getElementById(globalAlertId);
	$(globalAlert).removeClass("alert-info");
	$(globalAlert).removeClass("alert-danger");
	
	if(tipo=="erro"){
		$(globalAlert).addClass("alert-danger");
	}
	if(tipo=="info"){
		$(globalAlert).addClass("alert-info");
	}
	$(globalAlert).html(msg);
	$(globalAlert).removeClass("Alertoculto");
	
	
}

function openModal(content){
	$('#globalModalContent').html(content);
	$('#globalModal').modal();
}