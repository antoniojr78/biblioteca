package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOColecao;
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
	
	
	@RequestMapping("/formColecao")
	public ModelAndView formColecao(ModelAndView mav){
		
		mav.addObject(new Colecao());
		mav.setViewName("cadColecao");
		return mav;
	}
	
	
	@RequestMapping("/salvarColecao")
	public ModelAndView salvarColecao(ModelAndView mav,@Valid Colecao c,BindingResult result){
		mav.setViewName("cadColecao");
		if(result.hasErrors()) return mav;
		
		colecaoService.inserir(c);
		
		mav.addObject("msg","Coleção cadastrada com sucesso");
		return mav;
	}	
	
	
	@RequestMapping("/editColecao/{id}")
	public ModelAndView editColecao(@PathVariable("id")Long id,ModelAndView mav){
		
		Colecao c = daoColecao.getById(id);
		mav.setViewName("errogenerico");
		if(c==null){
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
		
		mav.setViewName("redirect:/listaColecao");
		
		return mav;
	}
	
	
	@RequestMapping("delColecao/{id}")
	public ModelAndView delColecao(@PathVariable("id") Long id,ModelAndView mav){
		
		Colecao c  = daoColecao.getById(id);
		mav.setViewName("errogenerico");
		if(c==null){
			mav.addObject("msg", "Registro não encontrado");
			return mav;
		}
		if(!alteracaoRegistroSecurityService.podeAlterar(c)){
			mav.addObject("msg", "Você não tem permissão para deletar esse registro");
			return mav;
		}
		
		
		daoColecao.deleta(c);

		
		mav.setViewName("redirect:/listaColecao");
		return mav;
	}
}
