package com.example.apmarkertadmin.model;

import java.io.Serializable;

public class Data implements Serializable {

    private String Name;
    private String Date;
    private String Type;
    private String PhNum;
    private int price;
    private String RoomArea;
    private String Contract;
    private String Description;
    private Address address;
    public Data() {
    }

    public Data(String name, String date,String phNum,Address addre){
        address = addre;
        Name = name;
        Date = date;
        PhNum = phNum;
    }

   /* public Data(String region, String townShip, String name, String date, String address) {
        Name = name;
        Date = date;
    }*/

    public Data(String name) {
        Name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPhNum() {
        return PhNum;
    }

    public void setPhNum(String phNum) {
        PhNum = phNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomArea() {
        return RoomArea;
    }

    public void setRoomArea(String roomArea) {
        RoomArea = roomArea;
    }

    public String getContract() {
        return Contract;
    }

    public void setContract(String contract) {
        Contract = contract;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
