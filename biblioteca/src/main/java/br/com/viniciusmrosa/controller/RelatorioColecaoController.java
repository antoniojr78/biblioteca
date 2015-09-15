package br.com.viniciusmrosa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.filtrosrel.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.services.RelatoriosService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioColecaoController {
	
	@Autowired
	private RelatoriosService relatorioService;
	
	@RequestMapping("/formRelColecoes")
	public String formRelColecao(Model model){
		
		model.addAttribute("filtros",new FiltroRelEntidadeBase());
		return "formRelColecoes";
	}
	
	@RequestMapping("/colecoes")
	public ModelAndView emitirRelcolecoes(ModelMap model,FiltroRelEntidadeBase filtros) 
			throws ErroRelatorioPDFException{
		
		model.put("arquivo_jasper","/reports/colecao/rel_colecoes.jasper");
		model.put("nome_arquivo_rel","relatorioColecoes.pdf");						
		model.put("NOME_REL", "Relatório de Coleções");
		
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		model.put("QUERY_PARAMETERS", queryParams);
		return relatorioService.gerarRelatorioSpring(model);
	}
}
