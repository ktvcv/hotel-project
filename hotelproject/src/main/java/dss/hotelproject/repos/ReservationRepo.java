package dss.hotelproject.repos;


import dss.hotelproject.Model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepo extends CrudRepository<Reservation, Long> {
    Reservation findReservationById(Long id);
    Reservation findReservationByConfirmationCode(String confirmationCode);
    Reservation findReservationByHotelIdAndConfirmationCode(Long id, String confirmationCode);
    List<Reservation> findAllByHotelId(Long id);

}
