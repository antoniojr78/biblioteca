package br.com.viniciusmrosa.report;

public enum FormatoExport {
	PDF("pdf"),HTML("html"),XLS("xls");
	
	private String format;

	private FormatoExport(String format) {
		this.format = format;
	}
	
	public String getFormat(){
		return this.format;
	}
}
