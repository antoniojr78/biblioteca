package br.com.viniciusmrosa.report;

public enum TemplateRel {
	
	
	TEMPLATE_PORTRAIT("template_portrait"),TEMPLATE_LANDSCAPE("template_landscape");
	private String nomeArquivoTemplate;
	
	
	TemplateRel(String nomeArquivo){
		this.nomeArquivoTemplate = nomeArquivo;
	}


	public String getNomeArquivoTemplate() {
		return nomeArquivoTemplate;
		
	}
	
	
	
}
