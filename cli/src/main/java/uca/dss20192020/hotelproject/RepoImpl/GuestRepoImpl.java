package uca.dss20192020.hotelproject.RepoImpl;


import uca.dss20192020.hotelproject.Interfaces.IGuestRepo;
import uca.dss20192020.hotelproject.Model.Guest;
import uca.dss20192020.hotelproject.Model.Hotel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
public class GuestRepoImpl implements IGuestRepo {

    HotelRepoImpl hotelRepo = new HotelRepoImpl();

    @Override
    public void addGuest(Long hotelId, Guest guest) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        if(hotel!=null) {
            List<Guest> guestList = hotel.getGuests();
            guestList.add(guest);

            hotel.setGuests(guestList);
            hotelRepo.saveHotel(hotel);
        }
    }

    @Override
    public Guest getGuestByDni(Long hotelId, String dni) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        Optional<Guest> guestOptional = Optional.empty();
        if(hotel!=null) {
            guestOptional = hotel.getGuests().stream().filter(x -> x.getDNI().equals(dni)).findAny();
        }
        return guestOptional.orElse(null);
    }

    @Override
    public boolean removeGuest(Long hotelId, Long id) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Guest> guests = hotel.getGuests();
        if(guests.removeIf(x -> x.getId().equals(id)))
        {
            hotel.setGuests(guests);
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean removeGuestByDNI(Long hotelId, String dni) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Guest> guests = hotel.getGuests();
        if(guests.removeIf(x -> x.getDNI().equals(dni)))
        {
            hotel.setGuests(guests);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean ifGuestExists(Long hotelId, String dni) {
        return getGuestByDni(hotelId, dni) != null;
    }

    @Override
    public List<Guest> getAllGuests(Long hotel_id) {
        Hotel hotel = hotelRepo.getHotel(hotel_id);
        if(hotel.getGuests() == null)
            return Collections.emptyList();
        return hotel.getGuests();
    }
}
