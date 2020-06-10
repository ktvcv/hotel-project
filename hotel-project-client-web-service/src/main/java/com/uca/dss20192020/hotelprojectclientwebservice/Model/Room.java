package com.uca.dss20192020.hotelprojectclientwebservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomStatus;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomType;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomTypeByMaxNumOfPerson;

public class Room{

    private Long id;
    private RoomType type;
    private RoomTypeByMaxNumOfPerson typeOfPerson;
    private RoomStatus roomStatus;

    private Guest guest;

    private Hotel hotel;

    private String roomNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomTypeByMaxNumOfPerson getTypeOfPerson() {
        return typeOfPerson;
    }

    public void setTypeOfPerson(RoomTypeByMaxNumOfPerson typeOfPerson) {
        this.typeOfPerson = typeOfPerson;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Room() {
    }

    public static final class RoomBuilder {
        private Long id;
        private RoomType type;
        private RoomTypeByMaxNumOfPerson typeOfPerson;
        private RoomStatus roomStatus;
        private Guest guest;
        private Hotel hotel;
        private String roomNumber;

        public RoomBuilder() {
        }

        public static RoomBuilder aRoom() {
            return new RoomBuilder();
        }

        public RoomBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RoomBuilder withType(RoomType type) {
            this.type = type;
            return this;
        }

        public RoomBuilder withTypeOfPerson(RoomTypeByMaxNumOfPerson typeOfPerson) {
            this.typeOfPerson = typeOfPerson;
            return this;
        }

        public RoomBuilder withRoomStatus(RoomStatus roomStatus) {
            this.roomStatus = roomStatus;
            return this;
        }

        public RoomBuilder withGuest(Guest guest) {
            this.guest = guest;
            return this;
        }

        public RoomBuilder withHotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public RoomBuilder withRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Room build() {
            Room room = new Room();
            room.setId(id);
            room.setType(type);
            room.setTypeOfPerson(typeOfPerson);
            room.setRoomStatus(roomStatus);
            room.setGuest(guest);
            room.setHotel(hotel);
            room.setRoomNumber(roomNumber);
            return room;
        }
    }
}
