package br.com.viniciusmrosa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.modelo.Usuario;
import br.com.viniciusmrosa.services.UsuarioService;
import br.com.viniciusmrosa.web.PaginacaoTabela;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DAOUsuario daoUsuario ;
	
	@RequestMapping("/cadUsuario")
	public String usuario(Model model){
		model.addAttribute("usuario",new Usuario());		
		return "cadUsuario";
	}
	
	@RequestMapping("/salvarUsuario")
	public String salvaUsuario(@Valid Usuario usuario, BindingResult result,Model model){
	
		//Bean validation
		if(result.hasErrors()) {		
			return "cadUsuario";
		}
			
		try {			
			usuarioService.inserirUsuario(usuario);
		} catch (RegistroExistenteException e) {
			// TODO Auto-generated catch block
			result.rejectValue("login", "chave.duplicada", e.getMessage());
			return "cadUsuario";
		}
				

		model.addAttribute("msg","Usuário adicionado com sucesso");
		model.addAttribute("usuario",new Usuario());
		return "cadUsuario";
	}
	
	@RequestMapping("/listaUsuario")
	public String listausuarios(Model model){
		
	
		
		List<Usuario> usuarios = daoUsuario.lista(0, 1000);
		System.out.println(usuarios.size());
		model.addAttribute("usuarios",usuarios);
		return "listaUsuarios";
	}
	
	
}
