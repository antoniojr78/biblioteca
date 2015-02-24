package br.com.viniciusmrosa.web;

public class PaginacaoTabela {

	private Integer offset;
	private Integer max;
	private String order;
	private Integer qtdRegistros;
	private Integer nropagina;
	private Integer Qtdpagina ;
	
	public Integer getQtdRegistros() {
		return qtdRegistros;
	}
	public void setQtdRegistros(Integer qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
	}
	public Integer getNropagina() {
		return nropagina;
	}
	public void setNropagina(Integer nropagina) {
		this.nropagina = nropagina;
	}
	public Integer getQtdpagina() {
		return Qtdpagina;
	}
	public void setQtdpagina(Integer qtdpagina) {
		Qtdpagina = qtdpagina;
	}
	public Integer getOffset() {
		return offset==null?0:offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getMax() {
		return max==null?5:max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public String getOrder() {
		return order==null?"asc":order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	
}
