package br.com.viniciusmrosa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
@Deprecated
public class ApplicationContextProvider implements ApplicationContextAware {
	
	private static ApplicationContext  context;
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	@Override
	public void setApplicationContext(ApplicationContext cx)
			throws BeansException {
		this.context = cx;
	}

}
