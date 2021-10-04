/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager;

import com.pruebaejb.pruebaentitymanager.DAO.NonTeachingStaffJpaController;
import com.pruebaejb.pruebaentitymanager.DAO.StaffJpaController;
import com.pruebaejb.pruebaentitymanager.DAO.TeachingStaffJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pryet
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("xxx");
        StaffJpaController staffDAO=new StaffJpaController(emf);
        staffDAO.create(new Staff( "Paco"));
        NonTeachingStaffJpaController nonTechingDAO=new NonTeachingStaffJpaController(emf);
        nonTechingDAO.create(new NonTeachingStaff("xxx", "area x"));
        TeachingStaffJpaController teachDAO=new TeachingStaffJpaController(emf);
        teachDAO.create(new TeachingStaff("teacher 1","profe de lengua", "klhsdgf"));
    }
}
