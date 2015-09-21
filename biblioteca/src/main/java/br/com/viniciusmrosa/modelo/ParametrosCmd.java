package br.com.viniciusmrosa.modelo;

import java.util.ArrayList;
import java.util.List;

public class ParametrosCmd {
	private List<ParametroSistema> parametros;
	
	public ParametrosCmd(List<ParametroSistema> parms){
		this.parametros = parms;
	}
	public ParametrosCmd() {
		if(this.parametros==null) {
			this.parametros = new ArrayList<>();
		}
	}
	

	public List<ParametroSistema> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroSistema> parametros) {
		this.parametros = parametros;
	}
	
	
}
