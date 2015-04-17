package br.com.viniciusmrosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOLivro;
import br.com.viniciusmrosa.modelo.Livro;

@Controller
public class LivroController {

	@Autowired
	private DAOLivro daoLivro;
	
	
	@RequestMapping(value="/cadLivro")
	public ModelAndView formLivro(ModelAndView mav){
		mav.addObject(createNewLivro());
		
		return mav;
	}
	
	@RequestMapping(value="/listaLivro")
	public ModelAndView listaLivro(ModelAndView mav){
		
		mav.addObject("livros",daoLivro.lista(0, 0));
		return mav;
	}
	
	private Livro createNewLivro(){
		return new Livro();
	}
}
