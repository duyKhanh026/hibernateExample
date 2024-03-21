/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hibernateexample;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author HP
 */
public class HibernateExample {
    private static SessionFactory factory;
    
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            
        }catch(Throwable ex){
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        HibernateExample hb2 = new HibernateExample();
        
        hb2.listDepartment();
    }
    public void listDepartment() {
        Session session = factory.openSession();
        Transaction tx = null;
         try{
             tx = session.beginTransaction();
             List employess = session.createQuery("FROM Department").list();
             for (Iterator iterator = employess.iterator(); iterator.hasNext();) {
                 Department dp = (Department) iterator.next();
                 System.out.print(" Name: " + dp.getName());
                 System.out.println(" StartDate: " + dp.getStartDate());
                 System.out.println(" Budget: " + dp.getBudget());
             }
         } catch (HibernateException e){
             if (tx != null) tx.rollback();
             e.printStackTrace();
         }
    }
}
