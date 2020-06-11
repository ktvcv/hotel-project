package uca.dss20192020.hotelproject.Model;

import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import uca.dss20192020.hotelproject.Enum.Services;
import uca.dss20192020.hotelproject.Payment.PaymentStrategy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Reservation implements Serializable {
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
    private PaymentStrategy paymentMethod;
    private boolean isApproved;

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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

    public PaymentStrategy getPaymentMethod() {
        return paymentMethod;
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

    public void setPaymentMethod(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
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
                ", servicesList=" + servicesList +
                ", paymentMethod=" + paymentMethod;
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
        private PaymentStrategy paymentMethod;
        private Boolean isApproved;

        private ReservationBuilder() {
        }

        public ReservationBuilder(String confirmationCode) {
            this.confirmationCode = confirmationCode;
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

        public ReservationBuilder withRoomTypeOfPerson(RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
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

        public ReservationBuilder withPaymentMethod(PaymentStrategy paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public ReservationBuilder withIsApproved(Boolean isApproved) {
            this.isApproved = isApproved;
            return this;
        }

        public Reservation build() {
            Reservation reservation = new Reservation();
            reservation.servicesList = this.servicesList;
            reservation.paymentMethod = this.paymentMethod;
            reservation.roomType = this.roomType;
            reservation.dateFrom = this.dateFrom;
            reservation.numberOfPersons = this.numberOfPersons;
            reservation.guest = this.guest;
            reservation.id = this.id;
            reservation.room = this.room;
            reservation.dateTo = this.dateTo;
            reservation.confirmationCode = this.confirmationCode;
            reservation.totalCost = this.totalCost;
            reservation.roomTypeByMaxNumOfPerson = this.roomTypeByMaxNumOfPerson;
            return reservation;
        }
    }
}