package com.danceschool.danceschool;

import com.danceschool.danceschool.database.Address;
import com.danceschool.danceschool.database.AddressController;
import com.danceschool.danceschool.database.AddressDao;
import com.danceschool.danceschool.database.AddressRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.GeneratedValue;
import java.util.List;


@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
            SpringApplication.run(DanceSchoolApplication.class, args);
        System.out.println("cos się dzieje");

        System.out.println("cos się dzieje");
        AddressDao addressDao = new AddressDao();
        Address address = new Address(@,"Kraków","30-818", "Wielicka","32","15");
        addressDao.saveAddress(address);

        List<Address> addresses = addressDao.getStudents();
        addresses.forEach(address1 -> System.out.println(address1.toString()));

    }
}
