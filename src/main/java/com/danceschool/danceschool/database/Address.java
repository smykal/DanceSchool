package com.danceschool.danceschool.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    private final int id;

    @Column(name = "city")
    private final String city;

    @Column(name = "postal_code")
    private final String postalCode;

    @Column(name = "street")
    private final String street;

    @Column(name = "block_number")
    private final String blockNumber;

    @Column(name = "apartment_number")
    private final String apartmentNumber;

    public Address(int id, String city, String postalCode, String street, String blockNumber, String apartmentNumber) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.blockNumber = blockNumber;
        this.apartmentNumber = apartmentNumber;
    }
}
