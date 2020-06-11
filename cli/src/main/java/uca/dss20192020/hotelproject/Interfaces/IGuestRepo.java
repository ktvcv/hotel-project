package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Model.Guest;

import java.util.List;

public interface IGuestRepo {
    void addGuest(Long hotelId, Guest guest);
    Guest getGuestByDni(Long hotelId, String dni);
    boolean removeGuest(Long hotelId, Long id);
    boolean removeGuestByDNI(Long hotelId, String dni);
    boolean ifGuestExists(Long hotelId, String dni);

    List<Guest> getAllGuests(Long hotel_id);
}
