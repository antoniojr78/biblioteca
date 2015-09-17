package br.com.viniciusmrosa.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.report.FiltroRelEntidadeBase;
import br.com.viniciusmrosa.report.FormatoExport;
import br.com.viniciusmrosa.services.RelatoriosService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;


@Controller("relatorios")
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
		model.put("nome_arquivo_rel","relatorioUsuarios.pdf");						
		model.put("NOME_REL", "Relatório de Usuários");
		model.put("format",filtros.getFormato().getFormat());
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		model.put("QUERY_PARAMETERS", queryParams);

		return relatorioService.gerarRelatorioSpring(model);

	}
	
	@RequestMapping("/formRelUsuarios")
	public String formRelUsuario(Model model){
		
		model.addAttribute("filros",new FiltroRelEntidadeBase());
		model.addAttribute("formatos",FormatoExport.values());
		
		return "formRelUsuario";
		
	}


	/*@RequestMapping("/usuarios2")
	public ModelAndView emitirRelaUsuario(ModelMap model,FiltroRelEntidadeBase filtros) throws ErroRelatorioPDFException, SQLException{
		
		model.put("arquivo_jasper","/reports/usuario/rel_usuarios.jasper");
		model.put("nome_arquivo_rel","relatorioUsuarios.pdf");						
		model.put("NOME_REL", "Relatório de Usuários");
		//System.out.println("Formato:"+ filtros.getFormato().getFormat());
		model.put("format",filtros.getFormato().getFormat());
		Map<String,Object> queryParams = new HashMap<String,Object>();			
		queryParams.put("parteNome", filtros.getParteNome());
		model.put("QUERY_PARAMETERS", queryParams);

		return relatorioService.gerarRelatorioSpring(model);

	}*/
}
