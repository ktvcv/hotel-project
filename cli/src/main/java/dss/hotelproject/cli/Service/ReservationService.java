package dss.hotelproject.cli.Service;

import dss.hotelproject.cli.Enum.RoomStatus;
import dss.hotelproject.cli.Interfaces.ICancellationService;
import dss.hotelproject.cli.Interfaces.ICheckInService;
import dss.hotelproject.cli.Interfaces.IReservationService;
import dss.hotelproject.cli.Model.Reservation;
import dss.hotelproject.cli.Model.Room;
import dss.hotelproject.cli.repos.HotelRepo;
import dss.hotelproject.cli.repos.ReservationRepo;
import dss.hotelproject.cli.repos.RoomRepo;
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
        ICancellationService, ICheckInService {

    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    PaymentService paymentService;
    @Autowired
    RoomRepo roomRepo;


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

    @Override
    public Reservation getReservationByCodeAndHotel(Long hotelId, String code) {
        return reservationRepo.findReservationByHotelIdAndConfirmationCode(hotelId,code);
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepo.save(reservation);
    }

    @Override
    public List<Reservation> getAllByHotelId(Long id) {
        return reservationRepo.findAllByHotelId(id);
    }

    /**
     * @param hotelId id of hotel where checkin occur
     * @param reservationCode: code of reservation
     * method is for checking-in , setting room for reservation etc
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





}
