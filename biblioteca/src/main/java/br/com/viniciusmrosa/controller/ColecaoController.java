package br.com.viniciusmrosa.controller;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOColecao;
import br.com.viniciusmrosa.exception.SistemaException;
import br.com.viniciusmrosa.modelo.Colecao;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;
import br.com.viniciusmrosa.services.ColecaoService;

@Controller
public class ColecaoController {

	@Autowired
	private DAOColecao daoColecao;
	
	@Autowired
	private ColecaoService  colecaoService;
	
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoRegistroSecurityService ;
	
	@RequestMapping("/listaColecao")
	public ModelAndView listaColecao(ModelAndView mav){
		mav.addObject("colecoes", daoColecao.lista(0, 0));
		mav.setViewName("listaColecao");
		return mav;
	}
	
	private void novo(ModelAndView mav){
		mav.addObject(new Colecao());
	}
	
	@RequestMapping("/formColecao")
	public ModelAndView formColecao(ModelAndView mav){
		
		//mav.addObject(new Colecao());
		novo(mav);
		mav.setViewName("cadColecao");
		return mav;
	}
	
	
	@RequestMapping("/salvarColecao")
	public ModelAndView salvarColecao(ModelAndView mav,@Valid Colecao c,BindingResult result){
		mav.setViewName("cadColecao");
		if(result.hasErrors()) return mav;
		
		colecaoService.inserir(c);
		novo(mav);
		mav.addObject("msg","Coleção cadastrada com sucesso");
		return mav;
	}	
	
	
	@RequestMapping("/editColecao/{id}")
	public ModelAndView editColecao(@PathVariable("id")Long id,ModelAndView mav){
		
		Colecao c = daoColecao.getById(id);
		if(c==null){
			mav.setViewName("errogenerico");
			mav.addObject("msg","Registro não encontrado");
			return mav;
		}
		
		mav.setViewName("editColecao");	
		mav.addObject(c);
		mav.addObject("podeAlterar",alteracaoRegistroSecurityService.podeAlterar(c));
		
		return mav;
	}
	
	@RequestMapping("/alteraColecao")
	public ModelAndView alteraColecao(@Valid Colecao c,ModelAndView mav,BindingResult result){
		mav.setViewName("editColecao");
		if(result.hasErrors()) return mav;
		
		
		try {
			colecaoService.alterar(c);
		} catch (SistemaException e) {
			mav.addObject("msg",e.getMessage());
			return mav;
		}
		mav.setViewName("redirect:/listaColecao");
		
		return mav;
	}
	
	@RequestMapping(value="/delColecao",method=RequestMethod.POST)
	@ResponseBody
	public String delColecao(Long id){
		
		String retorno  = "OK";
		try {			
			colecaoService.excluir(id);	
		} catch (SistemaException e) {
			retorno = e.getMessage();
		}
		return retorno;
	}
	@RequestMapping(value="delColecao/{id}",method=RequestMethod.GET)
	public ModelAndView delColecao(@PathVariable("id") Long id,ModelAndView mav){
		
		mav.setViewName("errogenerico");
		try {
			colecaoService.excluir(id);			
		} catch (SistemaException e) {
			mav.addObject("msg",e.getMessage());
		}
		
		mav.setViewName("redirect:/listaColecao");
		return mav;
	}
}
