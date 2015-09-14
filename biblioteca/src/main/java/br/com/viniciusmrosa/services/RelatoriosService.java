package br.com.viniciusmrosa.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;

public interface RelatoriosService {

	void gerarRelatorio(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parametros)
			throws ErroRelatorioPDFException;
	
	ModelAndView gerarRelatorioSpring(ModelMap modelMap) throws ErroRelatorioPDFException;

}