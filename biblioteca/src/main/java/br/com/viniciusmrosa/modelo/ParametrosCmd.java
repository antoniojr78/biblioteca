package br.com.viniciusmrosa.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParametrosCmd {
	private List<ParametroSistema> parametros;
	private Map<String, String> mapaParametros = new HashMap<String, String>();
	public ParametrosCmd(List<ParametroSistema> parms) {
		this.parametros = parms;
	}

	public ParametrosCmd() {
		if (this.parametros == null) {
			this.parametros = new ArrayList<>();
		}
	}

	public List<ParametroSistema> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroSistema> parametros) {
		this.parametros = parametros;
	}

	public Map<String,String> getAsMap(){
		if(this.parametros!=null){
			for(ParametroSistema parm : this.parametros){
				mapaParametros.put(parm.getCodParam(), parm.getValorParam());
			}
		}
		
		return mapaParametros;
	}
}
