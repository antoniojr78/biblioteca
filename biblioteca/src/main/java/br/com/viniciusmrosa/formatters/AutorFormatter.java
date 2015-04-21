package br.com.viniciusmrosa.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.Autor;

@Component
public class AutorFormatter implements Formatter<Autor>{

	
	@Override
	public String print(Autor autor, Locale locale) {
		
		return autor.getId()!=null?autor.getId().toString():null;
	}

	@Override
	public Autor parse(String id, Locale locale) throws ParseException {

		if(id!=null && !id.isEmpty()){
			Autor a = new Autor();
			a.setId(Long.parseLong(id));
			return a;
		}
		return null;
	}

}
