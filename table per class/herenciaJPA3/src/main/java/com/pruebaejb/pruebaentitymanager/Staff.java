/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author pryet
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String sname;

    public Staff(String sname) {
        super();
        
        this.sname = sname;
    }

    public Staff() {
        super();
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "Staff{" + "sid=" + sid + ", sname=" + sname + '}';
    }

}
