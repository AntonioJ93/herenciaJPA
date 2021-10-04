/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author pryet
 */
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int eid;
    private String ename;
    private double salary;
    private String deg;

    @ManyToOne
    @JoinColumn(name = "Department")
    private Department department;

    public Employee( String ename, double salary, String deg) {
        super();

        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
    }

    public Employee(String ename, double salary, String deg, Department department) {
        this.ename = ename;
        this.salary = salary;
        this.deg = deg;
        this.department = department;
    }
    

    public Employee() {
        super();
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", deg=" + deg +'}';
    }

  

}
