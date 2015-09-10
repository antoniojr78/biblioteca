package br.com.viniciusmrosa.services;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.exception.ErroRelatorioPDFException;
import br.com.viniciusmrosa.report.TemplateRel;
import br.com.viniciusmrosa.security.SecurityUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service("relatorioService")
public class RelatoriosServiceImpl implements RelatoriosService {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityUtils securityUtils;
	
	private String arquivoJasper;
	private String nomeArquivo;
	private final static String KEY_ARQUIVO_JASPER = "arquivo_jasper";
	private final static String KEY_NOME_ARQUIVO_REL = "nome_arquivo_rel";
	private final static String KEY_USUARIO_LOGADO = "usuario_logado";
	
	final static Logger logger = Logger.getLogger(RelatoriosServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see br.com.viniciusmrosa.services.RelatoriosService#gerarRelatorio(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.util.Map)
	 */
	@Override
	public void gerarRelatorio(HttpServletRequest request, HttpServletResponse response,Map<String, Object> parametros) throws ErroRelatorioPDFException{
		arquivoJasper = (String) parametros.get(KEY_ARQUIVO_JASPER);
		nomeArquivo = (String) parametros.get(KEY_NOME_ARQUIVO_REL);
		
		
		if(null==arquivoJasper) throw new ErroRelatorioPDFException("Parâmetro " + KEY_ARQUIVO_JASPER +  " é obrigatório");
		
		if(null==nomeArquivo) {
			nomeArquivo = "Relatorio.pdf";
			logger.warn("Parâmetro " + KEY_NOME_ARQUIVO_REL +  " não definido. Utilizando nome de aruqivo padrão");
		}		
		parametros.put("ARQUIVO_REL", arquivoJasper);
		parametros.put(JRParameter.REPORT_LOCALE, request.getLocale());
		parametros.put(KEY_USUARIO_LOGADO,securityUtils.buscaUsuarioLogado().getId());
		try {
			java.io.InputStream jasperStream = this.getClass()
					.getResourceAsStream(TemplateRel.TEMPLATE_PORTRAIT.getNomeArquivoTemplate());
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());
			//this.relatorioGerado = jasperPrint.getPages().size() > 0;
			//if (isRelatorioGerado()) {
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition", "inline; filename="+nomeArquivo);

				final OutputStream outStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			//}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ErroRelatorioPDFException(e.getMessage());
		}
	}
	
	
}