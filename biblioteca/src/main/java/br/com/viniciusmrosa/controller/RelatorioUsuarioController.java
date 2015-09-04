package br.com.viniciusmrosa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.filtrosrel.FiltroRelEntidadeBase;


@Controller("relatorios")
@RequestMapping("/relatorios")
@Transactional(readOnly=true)
public class RelatorioUsuarioController {


	@Autowired
	private DataSource dataSource;
	private boolean relatorioGerado;
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public void relUsuarios(HttpServletRequest request, HttpServletResponse response, FiltroRelEntidadeBase filtros){
		
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			System.out.println("RelatorioUsuarioController:" + filtros.getParteNome());
			parametros.put(JRParameter.REPORT_LOCALE, request.getLocale());
			parametros.put("NOME", filtros.getParteNome());
			try {
				java.io.InputStream jasperStream = this.getClass()
						.getResourceAsStream("/resources/reports/usuario/rel_usuarios.jasper");
				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
				this.relatorioGerado = jasperPrint.getPages().size() > 0;
				//if (isRelatorioGerado()) {
					response.setContentType("application/pdf");
					response.setHeader("Content-disposition", "inline; filename=relUsuarios.pdf");

					final OutputStream outStream = response.getOutputStream();
					JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
				//}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("exception");
				e.printStackTrace();
			}
			
		
	}
	
	@RequestMapping("/formRelUsuarios")
	public String formRelUsuario(Model model){
		
		model.addAttribute("filros",new FiltroRelEntidadeBase());
		return "formRelUsuario";
		
	}

	private boolean isRelatorioGerado() {
		// TODO Auto-generated method stub
		return this.relatorioGerado;
	}
}
