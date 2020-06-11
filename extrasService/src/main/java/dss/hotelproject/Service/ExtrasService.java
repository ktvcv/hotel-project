package dss.hotelproject.Service;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.Services;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Payment.PaymentStrategy;
import dss.hotelproject.repos.GuestRepo;
import dss.hotelproject.repos.ReservationRepo;
import dss.hotelproject.repos.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtrasService  {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    GuestRepo guestRepo;


    public boolean pay(PaymentStrategy paymentMethod, double cost) {
        paymentMethod.chargeMoney(cost);
        return true;
    }

    public Guest getGuestByDni(String dni) {
        return guestRepo.findGuestByDNI(dni);
    }

    public void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        reservation.setPaymentMethod(paymentStrategy);
        reservationRepo.save(reservation);
    }

    public void addServiceToReservation(Reservation reservation, Services services) {
        List<Services> servicesList = reservation.getServicesList();
        servicesList.add(services);
        reservation.setServicesList(servicesList);
        reservationRepo.save(reservation);
    }

    /**
     *
     * @param hotelId :
     * @param reservationCode: code fo reservation to delete, set Free status to room, which was occupied
     */
    public double checkOut(Long hotelId, String reservationCode) {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        Room room = reservation.getRoom();
        if (calculateAdditionalPayment(reservation) > 0)
            pay(reservation.getPaymentMethod(), calculateAdditionalPayment(reservation));

        room.setRoomStatus(RoomStatus.FREE);
        room.setGuest(null);

        reservationRepo.delete(reservation);
        roomRepo.save(room);
        return calculateAdditionalPayment(reservation);
    }

    public double calculateAdditionalPayment(Reservation reservation)
    {
        List<Services> servicesList = reservation.getServicesList();
        double additionalCost = 0;
        if(servicesList.isEmpty())
            return additionalCost;

        for (Services service: servicesList)
            additionalCost +=service.getServicePrice();

        return additionalCost;
    }
    public Reservation getReservationByCodeAndHotel(Long hotelId, String code) {
        return reservationRepo.findReservationByHotelIdAndConfirmationCode(hotelId,code);
    }


}
