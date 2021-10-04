/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager;

import com.pruebaejb.pruebaentitymanager.DAO.DepartmentJpaController;
import com.pruebaejb.pruebaentitymanager.DAO.EmployeeJpaController;
import com.pruebaejb.pruebaentitymanager.modelo.Department;
import com.pruebaejb.pruebaentitymanager.modelo.Employee;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pryet
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("xxx");
        DepartmentJpaController depDAO=new DepartmentJpaController(emf);
        depDAO.create(new Department("Ventas"));
        EmployeeJpaController empDAO=new EmployeeJpaController(emf);
        empDAO.create(new Employee("Paco", 2000, "asfhdh"));
        Employee e=empDAO.findEmployee(1);
        e.setDepartment(depDAO.findDepartment(1));
        try {
            empDAO.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(depDAO.findDepartment(1).getEmpleados());
        System.out.println(e.getDepartment());
        
    }
}
