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

import net.sf.jasperreports.engine.export.JRHtmlExporterConfiguration;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

@Configuration
public class ReportViewResolverConfig {


	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver(WebApplicationContext context){
		

		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/reports/templates/");
		resolver.setSuffix(".jasper");
		resolver.setReportDataKey("datasource");
		resolver.setViewNames("template_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);		
		Map<String, Object> exporterParameters = new HashMap<>();
		
			
		exporterParameters.put("net.sf.jasperreports.engine.export.JRHtmlExporterParameter.IMAGES_URI",context.getServletContext().getContextPath()+"/static/images/reports/");
		resolver.setExporterParameters(exporterParameters);
		resolver.setOrder(0);
		return resolver;
	}
	
}
