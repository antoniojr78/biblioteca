package br.com.viniciusmrosa.report;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public class CustomJasperReportsMultiFormatView extends JasperReportsMultiFormatView {

	/**
	 * Reescrevi o método para utilizar o servlet de imagens do jasper para que as imagens blob provenientes do banco aparecessem
	 * 
	 */
	@Override
	protected void renderReport(JasperPrint populatedReport, Map<String, Object> model, HttpServletResponse response)
			throws Exception {
		//Ao chamar o relatório deve ser setado a request no modelo.
		if(model.containsKey("requestObject")){
			HttpServletRequest request = (HttpServletRequest) model.get("requestObject");
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, populatedReport);
		}
		super.renderReport(populatedReport, model, response);
	}
}
