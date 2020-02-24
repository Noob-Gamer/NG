package com.example.apmarkertadmin.model;

public class Address {
    private String Region;
    private String TownShip;
    private String Detailaddress;
    private double location;

    public Address(String region, String townShip, String address, double location) {
        Region = region;
        TownShip = townShip;
        Detailaddress = address;
        this.location = location;
    }

    public Address(String region, String townShip, String detailaddress) {
        Region = region;
        TownShip = townShip;
        Detailaddress = detailaddress;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getTownShip() {
        return TownShip;
    }

    public void setTownShip(String townShip) {
        TownShip = townShip;
    }

    public String getDetailaddress() {
        return Detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        Detailaddress = detailaddress;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }
}
