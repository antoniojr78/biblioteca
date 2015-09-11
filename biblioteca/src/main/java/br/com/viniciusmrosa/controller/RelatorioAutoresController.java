package br.com.viniciusmrosa.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.filtrosrel.FiltroRelEntidadeBase;
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
	@ResponseBody
	public void emitirRelAutores(ModelAndView mav, FiltroRelEntidadeBase filtros, HttpServletRequest request,HttpServletResponse response) 
			throws ErroRelatorioPDFException{
		/**
		 * Exception será tratada pelo ExceptionHandlerController
		 */
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("arquivo_jasper","/resources/reports/autor/rel_autores.jasper");
		parametros.put("nome_arquivo_rel","relatorioAutores.pdf");						
		parametros.put("NOME_REL", "Relatório de Autores");
		
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		parametros.put("QUERY_PARAMETERS", queryParams);
		//try {
			relatorioService.gerarRelatorio(request, response, parametros);
		//	return null;
		//} catch (ErroRelatorioPDFException e) {
			// TODO Auto-generated catch block
			
		//	String url = request.getScheme()+ "://" + request.getLocalName() ;			
		//	url += ":" + request.getServerPort();
		//	url += request.getContextPath() +"/errogenerico";
		//	System.out.println("URL: "+ url);
		//	mav.addObject("msg",e.getMessage());
		//	mav.setViewName("errogenerico");
			
			
		//}
		
		
	}
}
