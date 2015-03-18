package br.com.viniciusmrosa.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

@Component
public class ProtecaoChavesService {

	@Autowired
	private HttpServletRequest servletRequest;
	
	public String getHashChave(Long id){
		 
	        final String password = servletRequest.getSession().getId();  
	        final String salt = KeyGenerators.string().generateKey();

	        TextEncryptor encryptor = Encryptors.text(password, salt);      
	        String encryptedId = encryptor.encrypt(id.toString());
	 
		
		return encryptedId;
	}
	
	
	public long getIdChave(String encryptedId){
		
        final String password = servletRequest.getSession().getId();  
        final String salt = KeyGenerators.string().generateKey();		
        TextEncryptor decryptor = Encryptors.text(password, salt);
        String decryptedText = decryptor.decrypt(encryptedId);
        
        return Long.parseLong(decryptedText);
	}
}
