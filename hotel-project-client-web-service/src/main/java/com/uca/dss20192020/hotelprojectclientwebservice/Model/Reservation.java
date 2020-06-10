package com.uca.dss20192020.hotelprojectclientwebservice.Model;

import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomType;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomTypeByMaxNumOfPerson;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.Services;

import java.time.LocalDate;
import java.util.List;

public class Reservation {

    private Long id;
    private Guest guest;
    private RoomType roomType;
    private RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson;

    private Room room;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int numberOfPersons;
    private double totalCost;
    private String confirmationCode;

    private List<Services> servicesList;

    private Hotel hotel;


    public void setId(Long id) {
        this.id = id;
    }


    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomTypeByMaxNumOfPerson getRoomTypeByMaxNumOfPerson() {
        return roomTypeByMaxNumOfPerson;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }


    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Reservation: " +
                "id=" + id +
                ", guest=" + guest +
                ", roomType=" + roomType +
                ", roomTypeOfPerson=" + roomTypeByMaxNumOfPerson +
                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", numberOfPersons=" + numberOfPersons +
                ", totalCost=" + totalCost +
                ", confirmationCode='" + confirmationCode + '\'' +
                ", servicesList=" + servicesList;
    }


    public static final class ReservationBuilder {
        private Long id;
        private Guest guest;
        private RoomType roomType;
        private RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson;
        private Room room;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private int numberOfPersons;
        private double totalCost;
        private String confirmationCode;
        private List<Services> servicesList;
        private Hotel hotel;

        private ReservationBuilder() {
        }

        public static ReservationBuilder aReservation() {
            return new ReservationBuilder();
        }

        public ReservationBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ReservationBuilder withGuest(Guest guest) {
            this.guest = guest;
            return this;
        }

        public ReservationBuilder withRoomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public ReservationBuilder withRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
            this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
            return this;
        }

        public ReservationBuilder withRoom(Room room) {
            this.room = room;
            return this;
        }

        public ReservationBuilder withDateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public ReservationBuilder withDateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public ReservationBuilder withNumberOfPersons(int numberOfPersons) {
            this.numberOfPersons = numberOfPersons;
            return this;
        }

        public ReservationBuilder withTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public ReservationBuilder withConfirmationCode(String confirmationCode) {
            this.confirmationCode = confirmationCode;
            return this;
        }

        public ReservationBuilder withServicesList(List<Services> servicesList) {
            this.servicesList = servicesList;
            return this;
        }

        public ReservationBuilder withHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public Reservation build() {
            Reservation reservation = new Reservation();
            reservation.setId(id);
            reservation.setGuest(guest);
            reservation.setRoomType(roomType);
            reservation.setRoomTypeByMaxNumOfPerson(roomTypeByMaxNumOfPerson);
            reservation.setRoom(room);
            reservation.setDateFrom(dateFrom);
            reservation.setDateTo(dateTo);
            reservation.setNumberOfPersons(numberOfPersons);
            reservation.setTotalCost(totalCost);
            reservation.setConfirmationCode(confirmationCode);
            reservation.setServicesList(servicesList);
            reservation.setHotel(hotel);
            return reservation;
        }
    }
}
