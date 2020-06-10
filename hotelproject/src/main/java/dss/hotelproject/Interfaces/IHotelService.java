package dss.hotelproject.Interfaces;

import dss.hotelproject.Model.Hotel;

public interface IHotelService {
    boolean ifHotelExists(Long id);
    void saveHotel(Hotel hotel);
    Hotel getHotelById(Long id);
}
