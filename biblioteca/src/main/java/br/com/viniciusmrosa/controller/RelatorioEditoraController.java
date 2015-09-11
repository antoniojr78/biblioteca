package br.com.viniciusmrosa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.filtrosrel.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.services.RelatoriosService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioEditoraController {

	@Autowired
	private RelatoriosService relatorioService;
	
	@RequestMapping("/formRelEditoras")
	public String formRelEditora(Model model){
	
		model.addAttribute("filtros",new FiltroRelEntidadeBase());
		return "formRelEditoras";
	}
	
	@RequestMapping("/editoras")
	public void emitirRelEditoras(HttpServletRequest request,HttpServletResponse response, FiltroRelEntidadeBase filtros) 
			throws ErroRelatorioPDFException{
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("arquivo_jasper","/resources/reports/editora/rel_editoras.jasper");
		parametros.put("nome_arquivo_rel","relatorioEditoras.pdf");						
		parametros.put("NOME_REL", "Relatório de Usuários");
		
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		parametros.put("QUERY_PARAMETERS", queryParams);
		relatorioService.gerarRelatorio(request, response, parametros);
	}
	
}
