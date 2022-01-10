package it.unisa.is.c09.digitaldonation.web.autoGenerate;

import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
