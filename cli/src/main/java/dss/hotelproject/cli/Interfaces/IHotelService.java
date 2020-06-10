package dss.hotelproject.cli.Interfaces;


import dss.hotelproject.cli.Model.Hotel;

public interface IHotelService {
    boolean ifHotelExists(Long id);
    void saveHotel(Hotel hotel);
    Hotel getHotelById(Long id);
}
