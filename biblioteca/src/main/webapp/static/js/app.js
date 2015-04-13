var globalAlertId = "globalAlert"
function removePorId(index,obj,url,elmsg){
	
	$("table tbody tr #icon-delete").click(function() { 		
		var linha = this.closest('tr');
		var id = obj.row(linha).data()[index];
		
		//Chamar a rotina de deleção via ajax
		$.post(url,{'id':id},function(data,status){
		
			if(data=='OK'){
				linha.remove();
			}else{ 
				showGlobalMsg('erro',data);
				}
		});
		
	});
}
function hideGlobalMsg(){
	
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

function openModal(){
	$('#myModal').modal();
}