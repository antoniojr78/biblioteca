package br.com.viniciusmrosa.report;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.jdbc.Work;

public class ExecutorRelatorio implements Work {

	private String caminhoRel;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeRel;
	private boolean relatorioGerado;

	@Override
	public void execute(Connection conn) throws SQLException {
		try {
			java.io.InputStream jasperStream = this.getClass()
					.getResourceAsStream(this.caminhoRel);
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parametros, conn);
			this.relatorioGerado = jasperPrint.getPages().size() > 0;
			if (isRelatorioGerado()) {
				response.setContentType("application/x-pdf");
				response.setHeader("Content-disposition", "inline; filename="
						+ this.nomeRel);

				final OutputStream outStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

	public ExecutorRelatorio(String caminhoRel, HttpServletResponse response,
			Map<String, Object> parametros, String nomeRel) {
		super();
		this.caminhoRel = caminhoRel;
		this.response = response;
		this.parametros = parametros;
		this.nomeRel = nomeRel;
	}

}
