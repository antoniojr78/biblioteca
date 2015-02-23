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
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/apresentacao")
	public String ola(Model model){
		
		
		return "index";
	}
	@RequestMapping("/cadUsuario")
	public String usuario(Model model){
		
		
		return "cadUsuario";
	}	
}
