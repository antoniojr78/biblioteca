package br.com.viniciusmrosa.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.Colecao;

@Component
public class ColecaoFormatter implements Formatter<Colecao> {

	@Override
	public String print(Colecao colecao, Locale locale) {
		return colecao.getId()!=null?colecao.getId().toString():null;
	}

	@Override
	public Colecao parse(String id, Locale locale) throws ParseException {
		if(id!=null && !id.isEmpty()){
			Colecao c = new Colecao();
			c.setId(Long.parseLong(id));
			return c;
		}
		return null;
	}

}
