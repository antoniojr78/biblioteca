package br.com.viniciusmrosa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
public class ReportViewResolverConfig {

	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver(){
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/reports/templates/");
		resolver.setSuffix(".jasper");
		resolver.setReportDataKey("datasource");
		resolver.setViewNames("template_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setOrder(0);
		return resolver;
	}
	
}
