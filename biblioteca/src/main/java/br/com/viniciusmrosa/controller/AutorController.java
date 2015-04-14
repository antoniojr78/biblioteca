package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.SistemaException;
import br.com.viniciusmrosa.modelo.Autor;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;
import br.com.viniciusmrosa.services.AutorService;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@Autowired
	private DAOAutor daoAutor;
	
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService ;
		
	@RequestMapping(value="/cadAutor")
	public ModelAndView formCadAutor( ModelAndView mav){
		mav.addObject("autor",new Autor());
		mav.setViewName("cadAutor");
		return mav;
	}
	
	
	@RequestMapping("/listaAutores")
	public ModelAndView listaAutores(ModelAndView mav){
		mav.addObject("autores",daoAutor.lista(0, 0));
		mav.setViewName("listaAutores");
		return mav;
	}
	
	@RequestMapping("/salvarAutor")
	public String salvarAutor(Model model,@Valid Autor autor,BindingResult result){
		
		if(result.hasErrors()) return "cadAutor";
	
		autorService.inserir(autor);
		
		model.addAttribute("msg","Autor cadastrado com sucesso");
		return "cadAutor";		
	}
	
	
	@RequestMapping("/editAutor/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,ModelAndView mav){
		
		Autor a = daoAutor.getById(id);
		if(a==null){
			mav.addObject("msg","Registro não encontrado");
			mav.setViewName("errogenerico");
			return mav;
		}
		mav.addObject(a);
		mav.setViewName("editAutor");
		mav.addObject("podeAlterar",alteracaoUsuarioSecurityService.podeAlterar(a));
		return mav;
	}
	
	@RequestMapping("/alterarAutor")
	public String alterarAutor(ModelAndView mav,@Valid Autor autor,BindingResult result){
		
		if(result.hasErrors()) return "editAutor";
		
		try {
			autorService.alterar(autor);
		} catch (SistemaException e) {
			mav.addObject("msg",e.getMessage());
			return "editAutor";
		} 
		
		
		return "redirect:/listaAutores";		
	}
	
	@RequestMapping(value="delAutor",method=RequestMethod.POST)
	@ResponseBody
	public String delAutor(Long id){
		
		try {
			autorService.excluir(id);
		} catch (SistemaException e) {
			return e.getMessage();
		}
		
		return "OK";
	}
	
	@RequestMapping(value="delAutor/{id}",method=RequestMethod.GET)
	public ModelAndView delUsuario(@PathVariable("id") Long id,ModelAndView mav){
		mav.setViewName("errogenerico");
	
		try {
			autorService.excluir(id);
		} catch (SistemaException e) {
			mav.addObject("msg",e.getMessage());
		}
		mav.setViewName("redirect:/listaAutores");
		return mav;
	}
}
