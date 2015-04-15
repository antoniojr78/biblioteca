package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOEditora;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.SistemaException;
import br.com.viniciusmrosa.modelo.Editora;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;
import br.com.viniciusmrosa.services.EditoraService;

@Controller
public class EditoraControler {

	@Autowired
	private EditoraService editoraService;
	
	@Autowired
	private DAOEditora daoEditora;
	
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoRegistroService;
	
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
		mav.addObject("msg","Editora inserida com sucesso");
		mav.addObject(novo());
		return mav;
	}
	
	@RequestMapping(value="/listaEditora")
	public ModelAndView listaEditora(ModelAndView mav){
		
		mav.setViewName("listaEditora");
		mav.addObject("editoras",daoEditora.lista(0, 0));
		return mav;
	}
	
	@RequestMapping(value="/editEditora/{id}")
	public ModelAndView editEditora(@PathVariable("id") Long id,ModelAndView mav,Editora editora){
		editora = daoEditora.getById(id);
		mav.setViewName("editEditora");
		
		if(editora ==null){
			mav.setViewName("errogenerico");
			mav.addObject("msg","Registro não encontrado");
			return mav;
		}
		mav.addObject("podeAlterar",alteracaoRegistroService.podeAlterar(editora));
		mav.addObject(editora);
		return mav;
	}
	
	@RequestMapping(value="/alteraEditora")
	public ModelAndView editEditora(ModelAndView mav, @Valid Editora editora,BindingResult result){
		
		
		mav.setViewName("editEditora");
		if(result.hasErrors()){
			return mav;
		}

		try {
			editoraService.alterar(editora);
		} catch (SistemaException e) {
			mav.addObject("msg",e.getMessage());
			return mav;
		}
		
		mav.setViewName("redirect:/listaEditora");
		return mav;
	}
	
	@RequestMapping(value="delEditora",method=RequestMethod.POST)
	@ResponseBody
	public String delEditora(Long id){
		System.out.println("excluir editora" + id);
		try {
			editoraService.excluir(id);
		} catch (SistemaException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "OK";
	}
	
	private Editora novo(){
		return new Editora();
	}
}
