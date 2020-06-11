package dss.hotelproject.repos;


import dss.hotelproject.Model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepo extends CrudRepository<Guest, Long> {
    Guest findGuestByDNI(String DNI);
    Guest findGuestById(Long id);

}
