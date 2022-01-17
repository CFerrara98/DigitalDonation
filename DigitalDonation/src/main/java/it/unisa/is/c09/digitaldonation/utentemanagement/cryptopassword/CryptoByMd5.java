package it.unisa.is.c09.digitaldonation.utentemanagement.cryptopassword;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La classe permette il criptaggio della password in MD5.
 *
 * @author Kevin Pacifico
 */
public class CryptoByMd5 {
  /**
   * Metodo che crypta la password.
   *
   * @param cleanPassword la password in chiaro
   * @return la password cryptata
   * @throws NoSuchAlgorithmException eccezione per l'algoritmo.
   */
  public static String getMd5(String cleanPassword) {
    if (cleanPassword != null) {
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
    } else {
      return "";
    }
  }
}
