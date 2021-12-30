package it.unisa.is.c09.digitaldonation.web.autoGenerate;

import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UtilServicePopulator {

    @Autowired
    DonatoreRepository donatoreRepository;
    @Autowired
    OperatoreRepository operatoreRepository;
    @Autowired
    SedeLocaleRepository sedeLocaleRepository;
    @Autowired
    IndisponibilitaRepository indisponibilitaRepository;
    @Autowired
    GuestRepository guestRepository;
    @Autowired
    SedutaRepository sedutaRepository;
    @Autowired
    DonazioneRepository donazioneRepository;

    public static String getMD5(String data) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }
}
