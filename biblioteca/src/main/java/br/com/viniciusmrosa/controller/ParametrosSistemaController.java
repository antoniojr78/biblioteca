package br.com.viniciusmrosa.controller;

import java.util.List;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOParametros;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.modelo.ParametroSistema;
import br.com.viniciusmrosa.modelo.ParametrosCmd;
import br.com.viniciusmrosa.services.ParametrosSistemaService;

@Controller
public class ParametrosSistemaController {

	@Autowired
	private DAOParametros daoParametros;
	
	@Autowired
	private ParametrosSistemaService parametrosService;

	@RequestMapping("/cadParametro")
	public String formCadParam(Model model){
		
		
		ParametrosCmd parametrosCmd = new ParametrosCmd(daoParametros.lista(0, 0));
		model.addAttribute("parametrosCmd",parametrosCmd);
		return "cadParametro";
	}
	
	@RequestMapping("/salvarParametro")
	public ModelAndView saveParms(ModelAndView mav, @ModelAttribute("parametrosCmd")ParametrosCmd parametroCmd,BindingResult result) {
		try {
			parametrosService.salvarParametros(parametroCmd.getParametros());
		} catch (PermissaoAlteracaoNegadaException e) {
			//mav.addObject("msg",e.getMessage());
			result.reject("permissao.negada", e.getMessage());
		}
		mav.setViewName("cadParametro");
		return mav;
	}
	
	
	@RequestMapping("/pegarValParam")
	@ResponseBody
	public String pegaValParam(){
		return "Meu valor";
	}
	
}
