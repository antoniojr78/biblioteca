package br.com.viniciusmrosa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController {
	
	@RequestMapping("/errogenerico")
	public ModelAndView goToErroGenerico(HttpServletRequest request,ModelAndView mav, String msg){
		if(null==msg) msg = request.getAttribute("msg").toString();
		mav.addObject("msg",msg);
		mav.setViewName("errogenerico");
		return mav;
	}
	
}
