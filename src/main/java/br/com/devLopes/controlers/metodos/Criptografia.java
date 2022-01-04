package br.com.devLopes.controlers.metodos;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

	//Criptografia da senha para o banco de dados utilizando o SHA-256
	static public String crip(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		
		byte[] messageDigest = algorithm.digest(password.getBytes("UTF-8"));
		

		StringBuilder sb = new StringBuilder();

		for (byte b : messageDigest) {
			sb.append(String.format("%02X", 0xFF & b));
		}

		return sb.toString();
	}
	
}
