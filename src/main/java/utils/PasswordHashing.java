package utils;

import it.cosenonjaviste.crypto.BCrypt;

public class PasswordHashing {
	public static String getPasswordHash(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public static boolean verifyPassword(String password, String hashPassword) {
		System.out.println(password);
		System.out.println(hashPassword);
		return BCrypt.checkpw(password, hashPassword);
	}

}
