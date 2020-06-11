package uca.dss20192020.hotelproject.Model;

import uca.dss20192020.hotelproject.Enum.RoomStatus;
import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Enum.RoomTypeByMaxNumOfPerson;

import java.io.Serializable;

public class Room implements Serializable {
    private Long id;
    private RoomType type;
    private RoomTypeByMaxNumOfPerson typeOfPerson;
    private RoomStatus roomStatus;
    private Guest guest;

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


    public static final class RoomBuilder {
        private Long id;
        private RoomType type;
        private RoomTypeByMaxNumOfPerson typeOfPerson;
        private RoomStatus roomStatus;
        private Guest guest;

        private RoomBuilder() {
        }

        public RoomBuilder(Long id)
        {
            this.id = id;
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

        public Room build() {
            Room room = new Room();
            room.setId(id);
            room.setType(type);
            room.setTypeOfPerson(typeOfPerson);
            room.setRoomStatus(roomStatus);
            room.setGuest(guest);
            return room;
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                ", typeOfPerson=" + typeOfPerson +
                ", roomStatus=" + roomStatus +
                ", guest=" + guest +
                '}';
    }
}
