package com.danceschool.danceschool;

import com.danceschool.danceschool.database.*;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class DanceSchoolApplication {
    public static void main(String[] args) {
            SpringApplication.run(DanceSchoolApplication.class, args);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.openSession();


        System.out.println("cos się dzieje");

        System.out.println("cos się dzieje");
        Address address = new Address(1, "3232", "fsse22", "fsse22", "fsse22", "fsse22");
        AddressDao addressDao = new AddressDao();
        addressDao.saveAddress(address);
        List<Address> addresses = addressDao.getAddress();
        addresses.forEach(address1 -> System.out.println(address1.toString()));

    }
}
