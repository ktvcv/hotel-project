package com.uca.dss20192020.hotelprojectclientwebservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomType;
import com.uca.dss20192020.hotelprojectclientwebservice.Enum.RoomTypeByMaxNumOfPerson;


import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Triple implements Serializable {
    private RoomType roomType;
    private RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson;
    private Integer number;
    private Double price;


    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomTypeByMaxNumOfPerson getRoomTypeByMaxNumOfPerson() {
        return roomTypeByMaxNumOfPerson;
    }

    public void setRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Triple(RoomType roomType, RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson, Integer number, Double price) {
        this.roomType = roomType;
        this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
        this.number = number;
        this.price = price;
    }

    public Triple() {
    }

    @Override
    public String toString() {
        return "Room: " +
                "type of room = " + roomType +
                ",room maximum for = " + roomTypeByMaxNumOfPerson.getNumberOfPersonsByType() + " persons" +
                ",price = " + roomType.getPriceByType() + "euros" +
                ", number of free room = " + number;
    }

}

