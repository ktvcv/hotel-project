package dss.hotelproject.cli.Service;

import dss.hotelproject.cli.Interfaces.IGuest;
import dss.hotelproject.cli.Model.Guest;
import dss.hotelproject.cli.repos.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService implements IGuest {

    @Autowired
    GuestRepo guestRepo;
    @Override
    public Guest getGuestByDni(String dni) {
        return guestRepo.findGuestByDNI(dni);
    }

    @Override
    public void saveGuest(Guest guest) {
        guestRepo.save(guest);
    }
}
