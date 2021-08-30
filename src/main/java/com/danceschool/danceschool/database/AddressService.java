package com.danceschool.danceschool.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address readAddress(int addressId) {
        System.out.println("szukamy w db rekordu z adresu o id: " + addressId);
        Address address = addressRepository.getOne(addressId);
        return address;
    }


}
