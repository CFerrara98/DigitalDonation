package it.unisa.is.c09.digitaldonation.UtenteManagement.cryptoPassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La classe permette il criptaggio della password in MD5.
 *
 * @author Kevin Pacifico
 */
public class CryptoByMD5 {
    /**
     * Metodo che crypta la password
     * @param cleanPassword la password in chiaro
     * @return la password cryptata
     * @throws NoSuchAlgorithmException
     */
    public static String getMD5(String cleanPassword)
    {
        if(cleanPassword != null) {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            messageDigest.update(cleanPassword.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString((int) (b & 0xff)));
            }
            return sb.toString();
        }
        else
            return "";
    }
}
