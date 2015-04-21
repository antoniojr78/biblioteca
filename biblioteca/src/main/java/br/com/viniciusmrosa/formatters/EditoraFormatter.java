package br.com.viniciusmrosa.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import br.com.viniciusmrosa.modelo.Editora;

@Component
public class EditoraFormatter implements Formatter<Editora> {

	@Override
	public String print(Editora editora, Locale locale) {
		// TODO Auto-generated method stub
		return editora.getId()!=null?editora.getId().toString():null;
	}

	@Override
	public Editora parse(String id, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		if(id!=null && !id.isEmpty()){
			Editora e = new Editora();
			e.setId(Long.parseLong(id));
			return e;
		}
		return null;
	}

}
