/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecciondtojpa.manytomany;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author pryet
 */
@Entity
public class Clas implements Serializable {
   @Id
   @GeneratedValue( strategy = GenerationType.IDENTITY )
   
   private int cid;
   private String cname;

   @ManyToMany(targetEntity=Teacher.class)
   private Set teacherSet;

   public Clas(){
      super();
   }
   
   public Clas(int cid, String cname, Set teacherSet) {
      super();
      this.cid = cid;
      this.cname = cname;
      this.teacherSet = teacherSet;
   }
   
   public int getCid(){
      return cid;
   }
   
   public void setCid(int cid) {
      this.cid = cid;
   }
   
   public String getCname() {
      return cname;
   }
   
   public void setCname(String cname) {
      this.cname = cname;
   }
   
   public Set getTeacherSet() {
      return teacherSet;
   }
   
   public void setTeacherSet(Set teacherSet) {
      this.teacherSet = teacherSet;
   }	  
}
