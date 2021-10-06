/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecciondtojpa.manytomany;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pryet
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("manyToMany");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        //Create Clas Entity
        Clas clas1 = new Clas(0, "1st", null);
        Clas clas2 = new Clas(0, "2nd", null);
        Clas clas3 = new Clas(0, "3rd", null);

        //Store Clas
        entitymanager.persist(clas1);
        entitymanager.persist(clas2);
        entitymanager.persist(clas3);

        //Create Clas Set1
        Set<Clas> classSet1 = new HashSet();
        classSet1.add(clas1);
        classSet1.add(clas2);
        classSet1.add(clas3);

        //Create Clas Set2
        Set<Clas> classSet2 = new HashSet();
        classSet2.add(clas3);
        classSet2.add(clas1);
        classSet2.add(clas2);

        //Create Clas Set3
        Set<Clas> classSet3 = new HashSet();
        classSet3.add(clas2);
        classSet3.add(clas3);
        classSet3.add(clas1);

        //Create Teacher Entity
        Teacher teacher1 = new Teacher(0, "Satish", "Java", classSet1);
        Teacher teacher2 = new Teacher(0, "Krishna", "Adv Java", classSet2);
        Teacher teacher3 = new Teacher(0, "Masthanvali", "DB2", classSet3);

        //Store Teacher
        entitymanager.persist(teacher1);
        entitymanager.persist(teacher2);
        entitymanager.persist(teacher3);

        Set<Teacher> teacherSet1 = new HashSet();
        Set<Teacher> teacherSet2 = new HashSet();
        Teacher teacher4 = new Teacher(0, "xxx", "www", null);
        Teacher teacher5 = new Teacher(0, "sss", "ddd", null);
        Teacher teacher6 = new Teacher(0, "aaa", "zzz", null);
        teacherSet1.add(teacher4);
        teacherSet2.add(teacher5);
        teacherSet2.add(teacher6);
        entitymanager.persist(teacher4);
        entitymanager.persist(teacher5);
        entitymanager.persist(teacher6);

        Clas clas4 = new Clas(0, "3rd", teacherSet1);
        Clas clas5 = new Clas(0, "3rd", teacherSet2);
        entitymanager.persist(clas4);
        entitymanager.persist(clas5);

        entitymanager.getTransaction().commit();
        entitymanager.close();
        emfactory.close();
    }
}
