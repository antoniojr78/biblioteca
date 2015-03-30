package br.com.viniciusmrosa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.modelo.BaseEntity;
import br.com.viniciusmrosa.modelo.Usuario;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;
import br.com.viniciusmrosa.services.UsuarioService;
import br.com.viniciusmrosa.web.PaginacaoTabela;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService ;
	
	@RequestMapping("/cadUsuario")
	public String usuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "cadUsuario";
	}

	@RequestMapping("/salvarUsuario")
	public String salvaUsuario(@Valid Usuario usuario, BindingResult result,
			Model model){

		// Bean validation
		if (result.hasErrors()) {
			return "cadUsuario";
		}

		try {
			usuarioService.inserirUsuario(usuario);
		} catch (RegistroExistenteException e) {
			// TODO Auto-generated catch block
			result.rejectValue("login", "chave.duplicada", e.getMessage());
			return "cadUsuario";
		}

		model.addAttribute("msg", "Usuário adicionado com sucesso");
		model.addAttribute("usuario", new Usuario());
		return "cadUsuario";
	}

	@RequestMapping("/listaUsuario")
	public String listausuarios(Model model) {

		List<Usuario> usuarios = daoUsuario.lista(0, 0);
		model.addAttribute("usuarios", usuarios);
		return "listaUsuarios";
	}

	
	@RequestMapping(value = "/editUsuario/{id}")	
	public ModelAndView preparaAltUsuario(@PathVariable(value = "id") Long id) {

		ModelAndView mav = new ModelAndView();
		Usuario u = daoUsuario.getById(id);
		if(u==null){
			mav.addObject("msg", "Registro não encontrado");
			mav.setViewName("errogenerico");
			return mav;
		}
		
		mav.addObject(u);
		mav.addObject("podeAlterar",alteracaoUsuarioSecurityService.podeAlterar(u));
		mav.setViewName("editUsuario");
		return mav;
	}

	@RequestMapping("/editarUsuario")
	public String editUsuario(@Valid @ModelAttribute Usuario usuario,
			BindingResult result, Model model) {

		// Bean validation
		if (result.hasErrors()) {
			return "editUsuario";
		}
		usuarioService.alterarUsuario(usuario);
		return "redirect:/listaUsuario";
	}
	
	
	@RequestMapping("/delusuario/{id}")
	public String deletarusuario(@PathVariable(value="id") Long id, Model model){
		

		Usuario u = daoUsuario.getById(id);
		if(u==null){
			model.addAttribute("msg", "Registro não encontrado");
			return "errogenerico";
		}
		if(!alteracaoUsuarioSecurityService.podeAlterar(u)){
			model.addAttribute("msg", "Você não tem permissão para deletar esse registro");
			return "errogenerico";
		}
		try{
			daoUsuario.deleta(u);
		}catch(DataIntegrityViolationException e){
			model.addAttribute("msg", "Não é possível deletar o registro pois há informações relacionadas utilizadas no sistema");
			return "errogenerico";
		}catch(Exception e){
			model.addAttribute("msg", "Ocorreu um erro ao deletar o registro");
			return "errogenerico";
		}
		
		return "redirect:/listaUsuario";
	}
}
