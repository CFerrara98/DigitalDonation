package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static it.unisa.is.c09.digitaldonation.utilRand.BuildRandEntity.*;

/**
 * Classe testing per la repository Guest
 */


@RunWith(MockitoJUnitRunner.class)
public class GuestRepositoryIT {

    @Mock
    private GuestRepository guestRepository;

    @Test
    public void saveTest(){
        Guest guest = createRandGuest();
        guestRepository.save(guest);
    }



}

