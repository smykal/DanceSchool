package com.danceschool.danceschool.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class AddressDao {
    public void saveAddress(Address address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(address);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < Address > getAddress() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Address", Address.class).list();
        }
    }
}