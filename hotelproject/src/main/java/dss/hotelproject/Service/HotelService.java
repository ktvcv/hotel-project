package dss.hotelproject.Service;

import dss.hotelproject.Interfaces.IHotelService;
import dss.hotelproject.Model.Hotel;
import dss.hotelproject.repos.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService implements IHotelService {

    @Autowired
    HotelRepo hotelRepo;

    @Override
    public boolean ifHotelExists(Long hotelId) {
        try {
            hotelRepo.findHotelById(hotelId);
        }catch (NullPointerException e)
        {
            return false;
        }
        return true;
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepo.findHotelById(id);
    }


}
