package com.danceschool.danceschool.database.repositories;

import com.danceschool.danceschool.database.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}