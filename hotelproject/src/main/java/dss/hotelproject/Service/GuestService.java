package dss.hotelproject.Service;

import com.netflix.discovery.converters.Auto;
import dss.hotelproject.Interfaces.IGuest;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.repos.GuestRepo;
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
