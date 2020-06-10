package dss.hotelproject.cli.Enum;

import java.io.Serializable;

public enum RoomType implements Serializable {
        STANDARD (30),
        BUSINESS (35),
        SUPERIOR (45);

        private double priceByType;

        // Creates a RoomType object
        RoomType(double priceByType){
            this.priceByType = priceByType;
        }

        // Getter to access to the price of each type of room
        public double getPriceByType(){ return priceByType; }

        public void setPriceByType(double priceByType) {
            this.priceByType = priceByType;
        }
    }
