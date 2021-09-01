package com.danceschool.danceschool.database;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "address")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int id;

    @Column(name = "city", table = "address")
    private String city;

    @Column(name = "postal_code", table = "address")
    private String postalCode;

    @Column(name = "street", table = "address")
    private String street;

    @Column(name = "block_number", table = "address")
    private String blockNumber;

    @Column(name = "apartment_number", table = "address")
    private String apartmentNumber;

}
