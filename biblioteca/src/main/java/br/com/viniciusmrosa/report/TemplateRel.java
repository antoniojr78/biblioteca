package br.com.viniciusmrosa.report;

public enum TemplateRel {
	
	
	TEMPLATE_PORTRAIT("template_portrait.jasper"),TEMPLATE_LANDSCAPE("template)landscape.jasper");
	private String nomeArquivoTemplate;
	
	
	TemplateRel(String nomeArquivo){
		this.nomeArquivoTemplate = nomeArquivo;
	}


	public String getNomeArquivoTemplate() {
		return "/resources/reports/templates/"+ nomeArquivoTemplate;
	}
	
	
	
}
