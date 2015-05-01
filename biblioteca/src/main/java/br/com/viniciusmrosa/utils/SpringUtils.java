package br.com.viniciusmrosa.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class SpringUtils {

	
	private static ApplicationContext context ;
	private static HttpServletRequest request;


	static{
		context = ApplicationContextProvider.getApplicationContext();
		request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
	}
	
	public static String getMessage(String code){
		return context.getMessage(code,null,request.getLocale());
	}
}
