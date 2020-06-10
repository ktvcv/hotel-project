package dss.hotelproject.cli.Interfaces;

public interface ICheckInService {
    boolean checkIn(String roomNumber, Long hotelId, String reservationCode);
}
