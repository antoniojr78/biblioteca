package br.com.viniciusmrosa.services;

import java.util.List;

import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.modelo.ParametroSistema;

public interface ParametrosSistemaService {

	void salvarParametros(List<ParametroSistema> parametros) throws PermissaoAlteracaoNegadaException;

}