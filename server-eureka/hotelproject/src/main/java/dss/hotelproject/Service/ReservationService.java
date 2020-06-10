package dss.hotelproject.Service;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Interfaces.ICancellationService;
import dss.hotelproject.Interfaces.ICheckInService;
import dss.hotelproject.Interfaces.IReservationManage;
import dss.hotelproject.Interfaces.IReservationService;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Payment.PaymentStrategy;
import dss.hotelproject.repos.HotelRepo;
import dss.hotelproject.repos.ReservationRepo;
import dss.hotelproject.repos.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationService implements IReservationService,
        ICancellationService, ICheckInService, IReservationManage {

    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    PaymentService paymentService;
    @Autowired
    RoomRepo roomRepo;

    /**
     * Method finds type of room, category of room
     * and number of free room in each type and category
     * @param hotelId : id of hotel in which searching room
     * @param firstDay : first day for searching
     * @param lastDay :
     * @param numberOfPersons: depends on which type of rooms will be found
     * @return : return list of 4 data types
     */

    /**
     * Method that cancel reservation before guest checked-in
     * @param hotelId : hotel id in which cancellation proceed
     * @param reservationCode : unique string-identifier od reservation
     * @return : if reservation was canceled successfully, return true
     */
    @Override
    public double cancelReservation(Long hotelId, String reservationCode) {
        Reservation reservation;
        try {
            reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        }catch (NullPointerException e) {
            e.printStackTrace();
            return -1;
        }
            paymentService.pay(reservation.getPaymentMethod(), calculateCancellationCharge(reservation));
            reservationRepo.delete(reservation);
            return calculateCancellationCharge(reservation);
        }


    /**
     * @param reservation
     * @return : amount of money to be paid
     */
    //Return the charge
    private double calculateCancellationCharge(Reservation reservation){
        LocalDate firstDay = reservation.getDateFrom();
        LocalDate dayOfCancellation = LocalDate.now();
        long dayBetween = ChronoUnit.DAYS.between(dayOfCancellation, firstDay);

        System.out.println(dayBetween);
        int percent;

        //system of calculating
        if (dayBetween > 30)
            percent = 0;
        else if (dayBetween >= 20)
            percent = 10;
        else if (dayBetween >= 10)
            percent = 30;
        else if (dayBetween >= 2)
            percent = 60;
        else
            percent = 100;

        return reservation.getTotalCost() * percent / 100;
    }

    public List<Reservation> getReservationsWithinDates(Long hotelId, LocalDate firstDay, LocalDate lastDay) {
        List<Reservation> allReservations = reservationRepo.findAllByHotelId(hotelId);
        List<Reservation> reservationsWithinDates = new ArrayList<>();

        allReservations.forEach(x -> {
            List<LocalDate> dates = Stream.iterate(x.getDateFrom(), date -> date.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(x.getDateFrom(), x.getDateTo().minusDays(1)))
                    .collect(Collectors.toList());
            if (dates.contains(firstDay) || dates.contains(lastDay.minusDays(1)))
                reservationsWithinDates.add(x);
        });

        return reservationsWithinDates;
    }

    /**
     * @param hotelId id of hotel where checkin occur
     * @param reservationCode: code of reservation
     */
    @Override
    public boolean checkIn(String roomNumber, Long hotelId, String reservationCode) {

        Room room = roomRepo.findRoomByRoomNumberAndHotelId(roomNumber, hotelId);
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);

        if(room.getRoomStatus() != RoomStatus.FREE){
            return false;
        }

        reservation.setRoom(room);

        room.setRoomStatus(RoomStatus.OCCUPIED);
        room.setGuest(reservation.getGuest());

        if(paymentService.pay(reservation.getPaymentMethod(),reservation.getTotalCost())) {
            roomRepo.save(room);
            reservationRepo.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public void changePaymentMethod(Long hotelId, String reservationCode, PaymentStrategy paymentStrategy) {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        reservation.setPaymentMethod(paymentStrategy);
        reservationRepo.save(reservation);
    }



}
