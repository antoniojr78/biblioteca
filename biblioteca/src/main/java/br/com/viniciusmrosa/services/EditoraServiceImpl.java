package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOEditora;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Editora;

@Service
public class EditoraServiceImpl extends AbstractService implements
		EditoraService {

	@Autowired
	private DAOEditora daoEditora;

	@Override
	public void inserir(Editora editora) {
		daoEditora.salva(editora);
	}

	@Override
	public void alterar(Editora editora) throws NegocioException,
			ErroOperacaoBDException {
		editora = daoEditora.getById(editora.getId());
		regrasBasicas(editora);		
		daoEditora.salva(editora);
	}

	@Override
	public void excluir(Long id) throws NegocioException,
			ErroOperacaoBDException {
		Editora editora = daoEditora.getById(id);
		regrasBasicas(editora);		
		daoEditora.salva(editora);

	}

}
