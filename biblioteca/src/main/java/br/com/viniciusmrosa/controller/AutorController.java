package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
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
	
		autorService.inserirAutor(autor);
		
		model.addAttribute("msg","Autor cadastrado com sucesso");
		return "cadAutor";		
	}
	
	
	@RequestMapping("/editAutor/{id}")
	public ModelAndView edit(@PathVariable("id") Long id,ModelAndView mav){
		
		Autor a = daoAutor.getById(id);
		
		mav.addObject(a);
		mav.addObject("podeAlterar",alteracaoUsuarioSecurityService.podeAlterar(a));
		mav.setViewName("editAutor");
		return mav;
	}
	
	@RequestMapping("/alterarAutor")
	public String alterarAutor(ModelAndView mav,@Valid Autor autor,BindingResult result){
		
		if(result.hasErrors()) return "editAutor";
		
		Autor autorBanco = daoAutor.getById(autor.getId());
		autorBanco.setNome(autor.getNome());
		daoAutor.salva(autorBanco);
		
		return "redirect:/listaAutores";		
	}
	
	
	@RequestMapping("delAutor/{id}")
	public ModelAndView delUsuario(@PathVariable("id") Long id,ModelAndView mav){
		mav.setViewName("errogenerico");
		Autor a = daoAutor.getById(id);
		if(a==null){
			mav.addObject("msg","Registro não encontrado");
			return mav;
		}
		
		if(!alteracaoUsuarioSecurityService.podeAlterar(a)){
			mav.addObject("msg","Você não tem permissão para deletar esse registro");
			return mav;
		}
		
		daoAutor.deleta(a);

		
		mav.setViewName("redirect:/listaAutores");
		return mav;
	}
}
