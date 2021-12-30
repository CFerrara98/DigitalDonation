package it.unisa.is.c09.digitaldonation.web.autoGenerate;


import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import static it.unisa.is.c09.digitaldonation.web.autoGenerate.BuildRandEntity.createRandIndisponbilita;
import static it.unisa.is.c09.digitaldonation.web.autoGenerate.RandChoiser.*;
import static it.unisa.is.c09.digitaldonation.web.autoGenerate.BuildRandEntity.*;
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

    public void testMD5(){
        Donatore d = createFullDonatore();
        donatoreRepository.save(d);
        System.out.println(d.toString());
    }


    public void doDonazioni(int stop){
        ArrayList<Donatore> donatores = (ArrayList<Donatore>) donatoreRepository.findAll();
        ArrayList<Seduta> sedutas = (ArrayList<Seduta>) sedutaRepository.findAll();

        for(int i= 0; i < stop ; i++){
            Donazione donazione = createRandDonazione();
            Seduta s = sedutas.get(randBetween(0,sedutas.size()-1));
            Donatore d = donatores.get(randBetween(0,donatores.size()-1));
            donazione.setTesserino(d.getTesserino());
            s.addPartecipante(d);

            donazioneRepository.save(donazione);
            sedutaRepository.save(s);
        }

    }


    public void doSedute(int stop){
        ArrayList<SedeLocale> sedi = (ArrayList<SedeLocale>) sedeLocaleRepository.findAll();
        ArrayList<Guest> guests = (ArrayList<Guest>) guestRepository.findAll();
        ArrayList<Donatore> donatores = (ArrayList<Donatore>) donatoreRepository.findAll();

        for(int start= 0 ; start < stop ; start++){
            Seduta seduta = createRandSeduta();
            seduta = sedutaRepository.save(seduta);
            seduta.setSedeLocale(sedi.get(randBetween(0,sedi.size()-1)));
            for(int i = randBetween(0,5); i > 0 ; i--) {
                Guest randGuest = guests.get(randBetween(0, guests.size()-1));
                seduta.addPartecipante(randGuest);
            }
            for(int i = randBetween(0,50); i > 0 ; i--) {
                Donatore randDonatore = donatores.get(randBetween(0, donatores.size()-1));
                seduta.addPartecipante(randDonatore);
            }
            sedutaRepository.save(seduta);
        }
    }


    public void doOperatoriAndSedeLocale(int stop){
        for(int i = 0 ; i < stop ; i++) {
            SedeLocale sedeLocale = createRandSedeLocale();
            sedeLocale = sedeLocaleRepository.save(sedeLocale);
            for (int num = 0; num < 6; num++) {
                Operatore o = createRandOperatore();
                o.setSedeLocale(sedeLocale);
                operatoreRepository.save(o);
            }
        }
    }

    public void doDonatori(int stop){
        for (int i = 0 ; i < stop ; i++){
            Donatore d = createFullDonatore();
            donatoreRepository.save(d);

            int stop2 = randBetween(0, 5);
            for (int i2 = 0; i2 < stop2; i2++) {
                Indisponibilita ind = createRandIndisponbilita();
                ind.setDonatore(donatoreRepository.findDonatoreByCodiceFiscaleUtente(d.getCodiceFiscale()));
                indisponibilitaRepository.save(ind);
            }
        }
    }
    public void doGuests(int stop){
        for (int i = 0 ; i < stop ; i++){
            guestRepository.save(createRandGuest());
        }
    }



}
