
package com.mycompany.hibernateexample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


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
        
        String startDatestr = "2008-10-01 08:00:00.0";
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDatestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Department department1 = new Department("Department 1", startDate, 10000, 1);
        Department department2 = new Department("Department 2", startDate, 15000, 2);
        
//      ví dụ về thêm department
//        hb2.addDepartment(department1);
//        hb2.addDepartment(department2);
            
        // ví dụ về sửa department
        department1 = new Department("Department 1 da sua", startDate, 15000, 1);
        department1.setDepartmentID(8);
//        hb2.updateDepartment(department1);
        
        //ví dụ về xóa department
//        hb2.deleteDepartment(9);
        
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
    public Integer addDepartment(Department dp) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer departmentID = null;
        try {
            tx = session.beginTransaction();
            Department department = new Department(dp.getName(), dp.getStartDate(), dp.getBudget(), dp.getAdministrator());
            departmentID = (Integer) session.save(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return departmentID;
    }
    public void updateDepartment(Department dp) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department) session.get(Department.class, dp.getDepartmentID());
            department.setName(dp.getName());
            department.setStartDate(dp.getStartDate());
            department.setBudget(dp.getBudget());
            department.setAdministrator(dp.getAdministrator());
            session.update(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    } 
    public void deleteDepartment(Integer departmentID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department) session.get(Department.class, departmentID);
            session.delete(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
