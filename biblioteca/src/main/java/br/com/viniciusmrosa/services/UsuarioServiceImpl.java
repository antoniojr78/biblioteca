package br.com.viniciusmrosa.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.ImpossivelApagarRegistroException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.exception.RegistroNaoEncontradoException;
import br.com.viniciusmrosa.modelo.Usuario;
import br.com.viniciusmrosa.security.AlteracaoRegistroSecurityService;
import br.com.viniciusmrosa.security.SecurityUtils;

@Service(value = "usuarioService")
public class UsuarioServiceImpl extends AbstractService implements
		UsuarioService {
	@Autowired
	private SecurityUtils securityUtils;
	
	@Autowired
	private DAOUsuario daoUsuario;

	private Usuario usuarioAlteracao;

	@Autowired
	private AlteracaoRegistroSecurityService alteracaoUsuarioSecurityService;

	@Override
	public void inserirUsuario(Usuario u) throws RegistroExistenteException {

		if (daoUsuario.usuarioExistente(u.getLogin()))
			throw new RegistroExistenteException("O login informado já existe");

		u.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		if(!securityUtils.buscaUsuarioLogado().isMaster())
			u.setMaster(false);
		daoUsuario.salva(u);

	}

	public void alterarUsuario(Usuario u) throws NegocioException,
			ErroOperacaoBDException {

		usuarioAlteracao = daoUsuario.getById(u.getId());
		checaExistente(usuarioAlteracao);
		checaPermissoes(usuarioAlteracao);
		usuarioAlteracao.setNome(u.getNome());
		usuarioAlteracao.setMaster(u.isMaster());
		
		// Se a senha for alterada, eu devo converter novamente em um hash
		if (!u.getSenha().equals(u.getSenhaHash())) {
			usuarioAlteracao.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		}
		daoUsuario.salva(usuarioAlteracao);
		
		//Substituir o nome pelo novo nome para refeletir a alteração no cabeçalho sem ter que fazer logout
		securityUtils.buscaUsuarioLogado().setNome(usuarioAlteracao.getNome());
	}

	@Override
	public void excluiUsuario(Long id) throws NegocioException,
			ErroOperacaoBDException {

		usuarioAlteracao = daoUsuario.getById(id);
		regrasBasicas(usuarioAlteracao);
		try {
			daoUsuario.deleta(usuarioAlteracao);
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelApagarRegistroException();
		}

	}

}
