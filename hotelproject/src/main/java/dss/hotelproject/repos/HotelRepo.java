package dss.hotelproject.repos;

import dss.hotelproject.Model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepo extends CrudRepository<Hotel, Long> {
    Hotel findHotelById(Long id);

}
