package com.uca.dss20192020.hotelprojectclientwebservice.Enum;

public enum Services {
    Breakfast(10),
    Parking(5),
    Laundry(3);

    private double servicePrice;

    // Creates a RoomType object
    Services(double priceByType){
        this.servicePrice = priceByType;
    }

    // Getter to access to the price of each type of room
    public double getServicePrice(){ return servicePrice; }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

}
