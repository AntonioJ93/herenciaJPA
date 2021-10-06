package com.proyecciondtojpa.manytomany;

import com.proyecciondtojpa.manytomany.Department;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-10-06T13:33:00", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, Integer> eid;
    public static volatile SingularAttribute<Employee, String> ename;
    public static volatile SingularAttribute<Employee, String> deg;
    public static volatile SingularAttribute<Employee, Double> salary;
    public static volatile SingularAttribute<Employee, Department> department;

}