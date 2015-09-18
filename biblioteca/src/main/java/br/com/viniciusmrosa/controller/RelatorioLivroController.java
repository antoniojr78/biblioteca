package br.com.viniciusmrosa.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.report.FiltrosRelLivro;
import br.com.viniciusmrosa.services.RelatoriosService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioLivroController {

	@Autowired
	private RelatoriosService relatorioService;
	
	@RequestMapping("/formRelLivros")
	public String formRelLivros(Model model){
		
		model.addAttribute("filtros", new FiltrosRelLivro());
		return "formRelLivros";
	}
	
	
	@RequestMapping("/livros")
	public ModelAndView relLivros(ModelMap model,FiltrosRelLivro filtros) throws ErroRelatorioPDFException{
	
		Map<String, String> imageMap = new HashMap<>();
		imageMap.put("img_0_0_1", "minhaimagem.jpg");
		model.put("NOME_REL", "Relatório de Livros");
		model.put("arquivo_jasper", "/reports/livro/rel_livros.jasper");
		model.put("format", filtros.getFormato().getFormat());
		Map<String, Object> query_params = new HashMap<>();
		query_params.put("parteTitulo", filtros.getParteNome());
		query_params.put("parteISBN", filtros.getParteISBN());
		query_params.put("parteAutor", filtros.getParteAutor());
		query_params.put("parteColecao", filtros.getParteColecao());
		query_params.put("parteEditora", filtros.getParteEditora());
		model.put("QUERY_PARAMETERS",query_params);
		return relatorioService.gerarRelatorioSpring(model);
	}
}
