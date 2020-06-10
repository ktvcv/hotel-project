package dss.hotelproject.cli.Interfaces;


import dss.hotelproject.cli.Model.Guest;

public interface IGuest {
    Guest getGuestByDni(String dni);
    void saveGuest(Guest guest);

}
