package br.com.viniciusmrosa.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.viniciusmrosa.modelo.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("/cadUsuario")
	public String usuario(Model model){
		model.addAttribute("usuario",new Usuario());		
		return "cadUsuario";
	}
	
	@RequestMapping("/salvarUsuario")
	public String salvaUsuario(@Valid Usuario usuario, BindingResult result,Model model){
		System.out.println("chamou metodo");
		
		
		if(result.hasErrors()) {
			System.out.println("deu erro");
			return "cadUsuario";
		}
		
		
		model.addAttribute("msg","Usuário adicionado com sucesso");
		model.addAttribute("usuario",new Usuario());
		return "cadUsuario";
	}
}
