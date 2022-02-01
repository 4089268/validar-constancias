package com.nerus.springboot.validardocs.app.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nerus.springboot.validardocs.app.models.Documento;

@Service
public class CodificadorDocs {
	
	private final String frase = "msi214ks9da4w67+0gsx102a+2asd56hpsa9mvxyada";
	
	public String codificarTexto(String contenido) throws Exception {
		Cipher aes = obtieneCipher(true);
		byte[] bytes = contenido.getBytes("UTF-8");
		byte[] encript = aes.doFinal(bytes);
		var b64 = new String(encript, "UTF-8");
		return b64;
	}
	public String decodificarTexto(String b64) throws Exception {		
		Cipher aes = obtieneCipher(false);
		byte[] encript = b64.getBytes("UTF-8");
		byte[] bytes = aes.doFinal(encript);
		String contenido = new String(bytes, "UTF-8");
		return contenido;
	}
	private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		digest.update(frase.getBytes("UTF-8"));
		SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
		Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
		if (paraCifrar) {
			aes.init(Cipher.ENCRYPT_MODE, key);
		} else {
			aes.init(Cipher.DECRYPT_MODE, key);
		}
		return aes;
	}
	
	public String serializarDocumento(Documento doc){		
		var gson = new Gson();
		var result = gson.toJson(doc);
		return result;
	}
	public Documento serializarDocumento(String docString){		
		var gson = new Gson();
		Documento result = gson.fromJson(docString, Documento.class);
		return result;
	}
}
