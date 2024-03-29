package br.com.viniciusmrosa.security;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component("controleAlteracaoRegistro")
public class ControleAlteracaoRegistroInterceptor extends
		HandlerInterceptorAdapter {
	
	private ModelAndView mav;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("ID:"+ request.getParameter("id"));
		HandlerMethod method = (HandlerMethod) handler;
		for (MethodParameter param :method.getMethodParameters()){
			System.out.println("Param:" +  param.getParameterName());
		}

		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerMethod method = (HandlerMethod) handler;
		for (MethodParameter param :method.getMethodParameters()){
			System.out.println("Param:" +  param.getParameterName());
		}
		
		System.out.println("post handleei a bagacita");
		modelAndView.addObject("MyObject","Sou fodaaaa");
		super.postHandle(request, response, handler, modelAndView);
	}
}
