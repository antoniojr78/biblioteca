package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.modelo.Editora;
import br.com.viniciusmrosa.services.EditoraService;

@Controller
public class EditoraControler {

	@Autowired
	private EditoraService editoraService;
	
	@RequestMapping(value="/formEditora")
	public ModelAndView formEditora( ModelAndView mav){
		mav.setViewName("cadEditora");
		mav.addObject(novo());
		return mav;
	}
	
	@RequestMapping(value="/salvarEditora")
	public ModelAndView inserirEditora(ModelAndView mav,@Valid Editora editora,BindingResult result){
		mav.setViewName("cadEditora");
		if(result.hasErrors())
			return mav;
		
		editoraService.inserir(editora);
		mav.addObject(novo());
		return mav;
	}
	
	
	private Editora novo(){
		return new Editora();
	}
}
