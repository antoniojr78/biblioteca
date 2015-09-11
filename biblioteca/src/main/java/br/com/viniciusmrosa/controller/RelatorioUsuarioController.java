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
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.filtrosrel.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.services.RelatoriosService;


@Controller("relatorios")
@RequestMapping("/relatorios")
@Transactional(readOnly=true)
public class RelatorioUsuarioController {

	@Autowired
	private RelatoriosService relatorioService;
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public void relUsuarios(HttpServletRequest request, HttpServletResponse response, FiltroRelEntidadeBase filtros) throws ErroRelatorioPDFException{
		
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("arquivo_jasper","/resources/reports/usuario/rel_usuarios.jasper");
			parametros.put("nome_arquivo_rel","relatorioUsuarios.pdf");						
			parametros.put("NOME_REL", "Relatório de Usuários");
			
			Map<String,Object> queryParams = new HashMap<String,Object>();			
			queryParams.put("parteNome", filtros.getParteNome());
			parametros.put("QUERY_PARAMETERS", queryParams);
			
			relatorioService.gerarRelatorio(request, response, parametros);

	}
	
	@RequestMapping("/formRelUsuarios")
	public String formRelUsuario(Model model){
		
		model.addAttribute("filros",new FiltroRelEntidadeBase());
		return "formRelUsuario";
		
	}


	@RequestMapping("/usuarios2")
	public JasperReportsMultiFormatView emitirRelaUsuario(FiltroRelEntidadeBase filtros) throws ErroRelatorioPDFException{
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("arquivo_jasper","classpath*:rel_usuarios.jasper");
		parametros.put("nome_arquivo_rel","relatorioUsuarios.pdf");						
		parametros.put("NOME_REL", "Relatório de Usuários");
		
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		parametros.put("QUERY_PARAMETERS", queryParams);
		
		return relatorioService.gerarRelatorioSpring(parametros);
		
	}
}
