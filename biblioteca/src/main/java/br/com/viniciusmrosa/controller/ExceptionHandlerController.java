package br.com.viniciusmrosa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.exception.RegistroExistenteException;

@ControllerAdvice
public class ExceptionHandlerController implements HandlerExceptionResolver {
	@Autowired
	HttpServletRequest req;
	
	final static Logger logger = Logger.getLogger(ExceptionHandlerController.class);

@ExceptionHandler(DataIntegrityViolationException.class)
public ModelAndView tratarIntegridadeDados(DataIntegrityViolationException e){
	ModelAndView mav = new ModelAndView();
	//mav.addObject("msg","Ocorreu um erro ao deletar o registro. O registro possui relação em outra parte do sistema");
	mav.addObject("msg","Erro inesperado: " + e.getMessage());
	mav.setViewName("errogenerico");
	return mav;
}


	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		ModelAndView mav = new ModelAndView();
		ex.printStackTrace();	
		mav.addObject("msg","Ocorreu um erro:<br />" + ex.getMessage());
		mav.setViewName("errogenerico");
		
		if(logger.isDebugEnabled())
			logger.debug(ex.getMessage(), ex);
			else logger.error(ex.getMessage(),ex);
		
		return mav;
	} 
}
