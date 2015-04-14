package br.com.viniciusmrosa.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.Usuario;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;

@Service(value = "usuarioService")
public class UsuarioServiceImpl extends AbstractService implements UsuarioService {

	@Autowired
	private DAOUsuario daoUsuario;

	private Usuario usuarioAlteracao;
	
	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService ;
	
	@Override
	public void inserirUsuario(Usuario u) throws RegistroExistenteException{
		
		if(daoUsuario.usuarioExistente(u.getLogin()))
			throw new RegistroExistenteException("O login informado já existe");
		
		u.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		daoUsuario.salva(u);
		
	}

	public void alterarUsuario(Usuario u) throws NegocioException,ErroOperacaoBDException {
		
		usuarioAlteracao = daoUsuario.getById(u.getId());
		checaExistente(usuarioAlteracao);
		checaPermissoes(usuarioAlteracao);
		usuarioAlteracao.setNome(u.getNome());
		// Se a senha for alterada, eu devo converter novamente em um hash		
		if (!u.getSenha().equals(u.getSenhaHash())) {
			usuarioAlteracao.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		}
		daoUsuario.salva(usuarioAlteracao);
	}

	@Override
	public void excluiUsuario(Long id) throws NegocioException,ErroOperacaoBDException {
		
		usuarioAlteracao = daoUsuario.getById(id);
		checaExistente(usuarioAlteracao);
		checaPermissoes(usuarioAlteracao);
		
		daoUsuario.deleta(usuarioAlteracao);
		
		
	}
	
}
