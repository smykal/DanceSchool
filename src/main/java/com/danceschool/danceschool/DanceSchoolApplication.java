package com.danceschool.danceschool;

import com.danceschool.danceschool.database.Address;
import com.danceschool.danceschool.database.AddressDao;
import com.danceschool.danceschool.database.AddressRepository;
import com.danceschool.danceschool.database.AddressService;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
            SpringApplication.run(DanceSchoolApplication.class, args);
        System.out.println("cos się dzieje");
        AddressDao addressDao = new AddressDao();
        Address address = new Address(4,"Kraków","30-818", "Wielicka","32","15");
        addressDao.saveStudent(address);

        List<Address> addresses = addressDao.getStudents();
        addresses.forEach(address1 -> System.out.println(address1.toString()));

    }
}
