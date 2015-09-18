package br.com.viniciusmrosa.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.report.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.services.RelatoriosService;

@Controller
@RequestMapping("/relatorios")
@Transactional(readOnly=true)
public class RelatorioAutoresController {
	
	@Autowired
	private RelatoriosService relatorioService;
	
	@RequestMapping("/formRelAutores")
	public String formRel(Model model){
		model.addAttribute("filtros",new FiltroRelEntidadeBase());		
		return "formRelAutores";
	}
	
	
	@RequestMapping("/autores")
	public ModelAndView emitirRelAutores(FiltroRelEntidadeBase filtros,ModelMap model) 
			throws ErroRelatorioPDFException{
		/**
		 * Exception será tratada pelo ExceptionHandlerController
		 */
		
		model.put("arquivo_jasper","/reports/autor/rel_autores.jasper");		
		model.put("NOME_REL", "Relatório de Autores");
		model.put("format",filtros.getFormato().getFormat());
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		model.put("QUERY_PARAMETERS", queryParams);

		return relatorioService.gerarRelatorioSpring(model);
				
	}
}
