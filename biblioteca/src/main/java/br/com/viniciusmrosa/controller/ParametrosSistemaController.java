package br.com.viniciusmrosa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOParametros;
import br.com.viniciusmrosa.modelo.ParametroSistema;
import br.com.viniciusmrosa.modelo.ParametrosCmd;

@Controller
public class ParametrosSistemaController {

	@Autowired
	private DAOParametros daoParametros;
	

	@RequestMapping("/cadParametro")
	public String formCadParam(Model model){
		
		
		ParametrosCmd parametrosCmd = new ParametrosCmd(daoParametros.lista(0, 0));
		model.addAttribute("parametrosCmd",parametrosCmd);
		return "cadParametro";
	}
	
	@RequestMapping("/salvarParametro")
	public ModelAndView saveParms(ModelAndView mav, @ModelAttribute("parametrosCmd")ParametrosCmd parametroCmd){
		List<ParametroSistema> parametros = parametroCmd.getParametros();
		for(ParametroSistema parms : parametros){
			System.out.println("Cod:" + parms.getCodParam());
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
