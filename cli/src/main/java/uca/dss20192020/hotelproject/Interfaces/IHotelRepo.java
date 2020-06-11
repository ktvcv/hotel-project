package uca.dss20192020.hotelproject.Interfaces;
import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Model.Hotel;


public interface IHotelRepo {
    Hotel getHotel(Long id);
    Hotel saveHotel(Hotel hotel);
}
