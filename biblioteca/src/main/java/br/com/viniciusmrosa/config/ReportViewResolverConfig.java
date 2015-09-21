package br.com.viniciusmrosa.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import br.com.viniciusmrosa.report.CustomJasperReportsMultiFormatView;
import net.sf.jasperreports.engine.export.JRHtmlExporterConfiguration;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

@Configuration
public class ReportViewResolverConfig {

	/**
	 * @param context
	 * @return JasperReportsViewResolver
	 * View Resolver utilizado para renderizar o retorno dos relatórios feitos pelo jasper Reports
	 */
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver(WebApplicationContext context){
		

		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/reports/templates/");
		resolver.setSuffix(".jasper");
		resolver.setReportDataKey("datasource");
		resolver.setViewNames("template_*");
		resolver.setViewClass(CustomJasperReportsMultiFormatView.class);		
		Map<String, Object> exporterParameters = new HashMap<>();					
	//	exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI",context.getServletContext().getContextPath()+"/livro/capa/1");
		exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_MAP",new HashMap<>());
		exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI",context.getServletContext().getContextPath()+"/relatorios/image?image=");
		exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN",Boolean.FALSE);		
		resolver.setExporterParameters(exporterParameters);
		resolver.setOrder(0);
		return resolver;
	}
	
}
