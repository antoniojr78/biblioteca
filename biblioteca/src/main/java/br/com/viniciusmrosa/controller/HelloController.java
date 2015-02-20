package br.com.viniciusmrosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.modelo.Usuario;

@Controller
public class HelloController {

	@Autowired
	private DAOUsuario daoUsuario;
	@RequestMapping("/")
	public String ola(Model model){
		
		Usuario u = daoUsuario.getById(1L);
		model.addAttribute("name",":" + u.getNome());
		
		return "index";
	}
	@RequestMapping("/cadUsuario")
	public String usuario(Model model){
		
		
		return "cadUsuario";
	}	
}
