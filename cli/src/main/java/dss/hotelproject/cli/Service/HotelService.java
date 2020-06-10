package dss.hotelproject.cli.Service;

import dss.hotelproject.cli.Interfaces.IHotelService;
import dss.hotelproject.cli.Model.Hotel;
import dss.hotelproject.cli.repos.HotelRepo;
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
