package br.com.viniciusmrosa.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.viniciusmrosa.dao.DAOAutor;
import br.com.viniciusmrosa.dao.DAOColecao;
import br.com.viniciusmrosa.dao.DAOEditora;
import br.com.viniciusmrosa.dao.DAOLivro;
import br.com.viniciusmrosa.modelo.Autor;
import br.com.viniciusmrosa.modelo.Colecao;
import br.com.viniciusmrosa.modelo.Editora;
import br.com.viniciusmrosa.modelo.Livro;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;

@Controller
public class LivroController {

	@Autowired
	private DAOLivro daoLivro;
	@Autowired
	private DAOAutor daoAutor;
	@Autowired
	private DAOEditora daoEditora;
	@Autowired
	private DAOColecao daoColecao;
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService;
	
	private List<Editora> listaEditoras;
	private List<Autor> listaAutores;
	private List<Colecao> listaColecoes;
	
	@RequestMapping(value="/cadLivro")
	public ModelAndView formLivro(ModelAndView mav){
		mav.addObject(createNewLivro());
		initCombosForm(mav);
		
		return mav;
	}
	
	@RequestMapping(value="/listaLivro")
	public ModelAndView listaLivro(ModelAndView mav){
		
		mav.addObject("livros",daoLivro.lista(0, 0));
		return mav;
	}
	
	
	@RequestMapping(value="/salvarLivro")
	public ModelAndView salvarLivro(@Valid Livro livro,BindingResult result,ModelAndView mav,@RequestPart(value="capa") MultipartFile foto){
		initCombosForm(mav);
		mav.setViewName("cadLivro");
		if(result.hasErrors()){
			return mav;
		}
		if(!foto.isEmpty()){
			System.out.println("Name:" +  foto.getName());
			System.out.println("Original Name:" +  foto.getOriginalFilename());
			try {
				livro.setFoto(foto.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mav.addObject("msg","Ocorreu um erro ao tentar salvar a foto de capa");
				return mav;
			}
		}
		daoLivro.salva(livro);
		mav.addObject("msg","Livro cadastrado com sucesso");
		
		mav.addObject(createNewLivro());
		return mav;
	}
	
	
	@RequestMapping(value="/editLivro/{id}")
	public ModelAndView formEditLivro(@PathVariable("id") Long id,ModelAndView mav){
		Livro livro = daoLivro.getById(id);
		if(livro==null){
			mav.addObject("msg","Registro não encontrado");
			mav.setViewName("errogenerico");
			return mav;
		}
		initCombosForm(mav);
		mav.addObject(livro);
		mav.setViewName("editLivro");
		mav.addObject("podeAlterar",alteracaoUsuarioSecurityService.podeAlterar(livro));
		return mav;
	}
	private void initCombosForm(ModelAndView mav){
		this.listaAutores = daoAutor.lista(0, 0);
		mav.addObject("listaAutores",this.listaAutores);
		
		this.listaEditoras = daoEditora.lista(0, 0);
		mav.addObject("listaEditoras",this.listaEditoras);
		
		this.listaColecoes = daoColecao.lista(0, 0);
		mav.addObject("listaColecoes",this.listaColecoes);		
	}
	private Livro createNewLivro(){
		return  new Livro();
	}
}
