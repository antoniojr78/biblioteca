package br.com.viniciusmrosa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOEditora;
import br.com.viniciusmrosa.exception.ErroOperacaoBDException;
import br.com.viniciusmrosa.exception.ImpossivelApagarRegistroException;
import br.com.viniciusmrosa.exception.NegocioException;
import br.com.viniciusmrosa.modelo.Editora;

@Service("editoraService")
public class EditoraServiceImpl extends AbstractService implements
		EditoraService {

	private Editora editoraAlteracao;

	@Autowired
	private DAOEditora daoEditora;

	@Override
	public void inserir(Editora editora) {
		daoEditora.salva(editora);
	}

	@Override
	public void alterar(Editora editora) throws NegocioException,
			ErroOperacaoBDException {
		editoraAlteracao = daoEditora.getById(editora.getId());
		regrasBasicas(editoraAlteracao);
		editoraAlteracao.setNome(editora.getNome());
		daoEditora.salva(editoraAlteracao);
	}

	@Override
	public void excluir(Long id) throws NegocioException,
			ErroOperacaoBDException {
		editoraAlteracao = daoEditora.getById(id);
		regrasBasicas(editoraAlteracao);
		try {
			daoEditora.deleta(editoraAlteracao);
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelApagarRegistroException();
		}
	}

}
