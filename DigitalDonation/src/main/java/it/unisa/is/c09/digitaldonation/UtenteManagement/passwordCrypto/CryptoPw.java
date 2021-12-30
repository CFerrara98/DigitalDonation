package it.unisa.is.c09.digitaldonation.UtenteManagement.passwordCrypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoPw {

	private static String ALGO = "SHA-256" ;
	
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String OUTPUT_FORMAT = "%-20s:%s";

    private static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGO);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * Metodo che controlla se la password passata combacia con quella cryptata presente sul db
     * @param pw password in chiaro, pw+token se è stato aggiunto il token alla password
     * @param pwStored password stored nel db
     * @return booleano True se combaciano, false altrimenti
     */
    public static boolean isEqual(String pw , String pwStored) {
    	String pass = CryptoPw.crypt(pw);
    	return pass.equals(pwStored);
    }

    /**
     * Metodo che crypta la password
     * @param str password da cryptare, può essere aggiunta con un token pw+token
     * @return password cryptata
     */
    public static String crypt(String str) {
    	byte[] shaInBytes = CryptoPw.digest(str.getBytes(UTF_8));
    	return bytesToHex(shaInBytes);
    }
        
    
}

    
    
    
