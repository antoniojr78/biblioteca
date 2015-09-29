package br.com.viniciusmrosa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.viniciusmrosa.dao.DAOParametros;
import br.com.viniciusmrosa.exception.PermissaoAlteracaoNegadaException;
import br.com.viniciusmrosa.modelo.ParametroSistema;

@Service("parametrosService")
public class ParametrosSistemaServiceImpl extends AbstractService implements ParametrosSistemaService {
	
	
	@Autowired
	private DAOParametros daoParametros;
	
	/* (non-Javadoc)
	 * @see br.com.viniciusmrosa.services.ParametrosSistemaService#salvarParametros(java.util.List)
	 */
	@Override
	public void salvarParametros(List<ParametroSistema> parametros) throws PermissaoAlteracaoNegadaException{
		checaPermissaoMaster();

		for(ParametroSistema parm : parametros){
			ParametroSistema parmOriginal =  daoParametros.getById(parm.getId());
			if(!parm.getValorParam().equals(parmOriginal.getValorParam())){
				parmOriginal.setValorParam(parm.getValorParam());
				daoParametros.salva(parmOriginal);
			}
		}
	}
}
