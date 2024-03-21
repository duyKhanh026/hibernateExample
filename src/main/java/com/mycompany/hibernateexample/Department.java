/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hibernateexample;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    private int departmentID;

    @Column(name = "Name")
    private String name;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "Budget")
    private double budget;

    @Column(name = "Administrator")
    private int administrator;

    // Constructors, getters, and setters
    public Department() {
    }

    public Department(String name, Date startDate, double budget, int administrator) {
        this.name = name;
        this.startDate = startDate;
        this.budget = budget;
        this.administrator = administrator;
    }

    // Getters and Setters
    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }
}