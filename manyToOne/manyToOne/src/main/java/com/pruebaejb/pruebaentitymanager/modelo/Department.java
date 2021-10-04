/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pryet
 */
@Entity
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "department",fetch =FetchType.EAGER)
    private List<Employee> empleados;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, List<Employee> empleados) {
        this.name = name;
        this.empleados = empleados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String deptName) {
        this.name = deptName;
    }

    public List<Employee> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Employee> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", empleados=" + empleados + '}';
    }

    

}
