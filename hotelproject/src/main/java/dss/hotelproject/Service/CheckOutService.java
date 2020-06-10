package dss.hotelproject.Service;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.Services;
import dss.hotelproject.Interfaces.ICheckOut;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.repos.ReservationRepo;
import dss.hotelproject.repos.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutService implements ICheckOut {

    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    PaymentService paymentService;
    @Autowired
    RoomRepo roomRepo;
    /**
     *
     * @param hotelId :
     * @param reservationCode: code fo reservation to delete, set Free status to room, which was occupied
     */
    @Override
    public double checkOut(Long hotelId, String reservationCode) {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        Room room = reservation.getRoom();
        if (calculateAdditionalPayment(reservation) > 0)
            paymentService.pay(reservation.getPaymentMethod(), calculateAdditionalPayment(reservation));

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


}
