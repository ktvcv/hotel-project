package dss.hotelproject.cli.repos;


import dss.hotelproject.cli.Model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepo extends CrudRepository<Guest, Long> {
    Guest findGuestByDNI(String DNI);
    Guest findGuestById(Long id);

}
