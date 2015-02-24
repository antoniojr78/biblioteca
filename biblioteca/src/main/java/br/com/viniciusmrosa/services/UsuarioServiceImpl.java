package br.com.viniciusmrosa.services;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOUsuario;
import br.com.viniciusmrosa.exception.RegistroExistenteException;
import br.com.viniciusmrosa.modelo.Usuario;

@Service(value="usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private DAOUsuario daoUsuario;
	
	/* (non-Javadoc)
	 * @see br.com.viniciusmrosa.services.UsuarioService#inserirUsuario(br.com.viniciusmrosa.modelo.Usuario)
	 */
	@Override
	public void inserirUsuario(Usuario u) throws RegistroExistenteException{
		
		if(daoUsuario.usuarioExistente(u.getLogin()))
			throw new RegistroExistenteException("O login informado j� existe");
		
		System.out.println(DigestUtils.sha512Hex(u.getSenha()).length());
		u.setSenha(DigestUtils.sha512Hex(u.getSenha()));
		daoUsuario.salva(u);
		
	}
}