package com.danceschool.danceschool.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class AddressDao {
    public void saveAddress(Address student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.save(student);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List < Address > getStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Address.class).list();
        }
    }
}