package com.gjohnson.homesite.general.address;

public class Address {

    private Integer addressId;
    private String primaryStreet;
    private String secondaryStreet;
    private String city;
    private String state;
    private String zipBase;
    private String zipExt;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getPrimaryStreet() {
        return primaryStreet;
    }

    public void setPrimaryStreet(String primaryStreet) {
        this.primaryStreet = primaryStreet;
    }

    public String getSecondaryStreet() {
        return secondaryStreet;
    }

    public void setSecondaryStreet(String secondaryStreet) {
        this.secondaryStreet = secondaryStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipBase() {
        return zipBase;
    }

    public void setZipBase(String zipBase) {
        this.zipBase = zipBase;
    }

    public String getZipExt() {
        return zipExt;
    }

    public void setZipExt(String zipExt) {
        this.zipExt = zipExt;
    }
}
