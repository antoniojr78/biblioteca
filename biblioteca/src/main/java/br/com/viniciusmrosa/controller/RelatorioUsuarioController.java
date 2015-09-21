package br.com.viniciusmrosa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.report.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.services.RelatoriosService;


@Controller()
@RequestMapping("/relatorios")
@Transactional(readOnly=true)
public class RelatorioUsuarioController {

	@Autowired
	private RelatoriosService relatorioService;
	
	@Autowired
	private DataSource dataSource;
	
	
	@RequestMapping("/usuarios")	
	public ModelAndView relUsuarios(ModelMap model,FiltroRelEntidadeBase filtros) throws ErroRelatorioPDFException{
		
		model.put("arquivo_jasper","/reports/usuario/rel_usuarios.jasper");				
		model.put("NOME_REL", "Relatório de Usuários");
		model.put("format",filtros.getFormato().getFormat());
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		model.put("QUERY_PARAMETERS", queryParams);

		return relatorioService.gerarRelatorioSpring(model);

	}
	
	@RequestMapping("/formRelUsuarios")
	public String formRelUsuario(Model model){
		
		model.addAttribute("filtros",new FiltroRelEntidadeBase());
		
		return "formRelUsuario";
		
	}


}
