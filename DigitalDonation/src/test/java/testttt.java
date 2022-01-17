import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testttt {


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

    public static void main(String[] args) {
        //System.out.println(getMD5("pdT!nW&6kGSS"));

    }


}
