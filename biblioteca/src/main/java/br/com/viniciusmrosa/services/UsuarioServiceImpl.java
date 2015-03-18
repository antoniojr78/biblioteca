package br.com.viniciusmrosa.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.modelo.Usuario;

@Service(value = "usuarioService")
public class UsuarioServiceImpl  implements UsuarioService {

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private LogCriacaoService logCriacaoService;
	
	private Usuario usuarioAlteracao;
	
	@Override
	public void inserirUsuario(Usuario u) throws RegistroExistenteException{
		
		if(daoUsuario.usuarioExistente(u.getLogin()))
			throw new RegistroExistenteException("O login informado já existe");
		
		u.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		u.setLogCriacao(logCriacaoService.getLogCriacao());		
		daoUsuario.salva(u);
		
	}

	public void alterarUsuario(Usuario u) {
		
		usuarioAlteracao = daoUsuario.getById(u.getId());
		usuarioAlteracao.setNome(u.getNome());
		// Se a senha for alterada, eu devo converter novamente em um hash		
		if (!u.getSenha().equals(u.getSenhaHash())) {
			usuarioAlteracao.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		}
		daoUsuario.salva(usuarioAlteracao);
	}

}
