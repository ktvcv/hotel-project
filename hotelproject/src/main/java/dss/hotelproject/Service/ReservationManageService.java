package dss.hotelproject.Service;

import dss.hotelproject.Enum.Services;
import dss.hotelproject.Interfaces.IReservationManage;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Payment.PaymentStrategy;
import dss.hotelproject.repos.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationManageService implements IReservationManage {

    @Autowired
    ReservationRepo reservationRepo;
    @Override
    public void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        reservation.setPaymentMethod(paymentStrategy);
        reservationRepo.save(reservation);
    }

    @Override
    public void addServiceToReservation(Reservation reservation, Services services) {
        List<Services> servicesList = reservation.getServicesList();
        servicesList.add(services);
        reservation.setServicesList(servicesList);
        reservationRepo.save(reservation);
    }


}
