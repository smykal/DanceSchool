package com.danceschool.danceschool.data;

public class Address {
    private String City;
    private String Street;
    private int code;

    public Address(String city, String street, int code) {
        City = city;
        Street = street;
        this.code = code;
    }
}
