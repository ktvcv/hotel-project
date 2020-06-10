package dss.hotelproject.Interfaces;

import dss.hotelproject.Model.Guest;

public interface IGuest {
    Guest getGuestByDni(String dni);
    void saveGuest(Guest guest);

}
