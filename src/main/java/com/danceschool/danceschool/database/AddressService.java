package com.danceschool.danceschool.database;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public List<Address> readAddress() {
        log.info("szukamy w db wszystkich: ");
        return addressRepository.findAll();
    }

    @GetMapping("/address/{id}")
    public Address readAddressById(@PathVariable String id) {
        log.info("looking forward in database specific: " + id + " id.");
        return addressRepository.findById(Integer.valueOf(id)).get();
    }
}
