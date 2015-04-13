package br.com.viniciusmrosa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.RegistroExistenteException;

@ControllerAdvice
public class ExceptionHandlerController implements HandlerExceptionResolver {
	@Autowired
	HttpServletRequest req;
	
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Data integrity violation") 	
@ExceptionHandler(DataIntegrityViolationException.class)
public ModelAndView tratarIntegridadeDados(DataIntegrityViolationException e){
	
	ModelAndView mav = new ModelAndView();
	mav.addObject("msg","Ocorreu um erro ao deletar o registro. O registro possui relação em outra parte do sistema");
	mav.setViewName("errogenerico");
	return mav;
}
/*	@ExceptionHandler(Exception.class)
	public ModelAndView tratarExcecoes(Exception e){
		
		System.out.println("req: " +  req.getRequestURI() + "--> " + req.getServletContext());
		ModelAndView mav = new ModelAndView("index");
		System.out.println("View:" + mav.getView());
		System.out.println("ViewName:" + mav.getViewName());
		mav.addObject("errMsg",e.getMessage());

		return mav;
		
	}

*/	

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//System.out.println(request.getHeader("Referer"));
		//HandlerMethod handlermeth = (HandlerMethod) handler;
		//System.out.println(handlermeth.get);
		
		ModelAndView mav = new ModelAndView();
		/*String view = null ;
		if(ex instanceof RegistroExistenteException){
			 view = request.getHeader("Referer").split("/")[request.getHeader("Referer").split("/").length-1];
			
			 mav.addObject("errMsg",ex.getMessage());
		}else{
			 view = "errogenerico";
		} */

		mav.addObject("msg","Ocorreu um erro:<br />" + ex.getMessage());
		mav.setViewName("errogenerico");
		return mav;
	} 
}
