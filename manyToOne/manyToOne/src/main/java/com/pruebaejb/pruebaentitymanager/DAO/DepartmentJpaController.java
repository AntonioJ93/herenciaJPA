/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager.DAO;

import com.pruebaejb.pruebaentitymanager.exceptions.NonexistentEntityException;
import com.pruebaejb.pruebaentitymanager.modelo.Department;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pruebaejb.pruebaentitymanager.modelo.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author pryet
 */
public class DepartmentJpaController implements Serializable {

    public DepartmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Department department) {
        if (department.getEmpleados() == null) {
            department.setEmpleados(new ArrayList<Employee>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Employee> attachedEmpleados = new ArrayList<Employee>();
            for (Employee empleadosEmployeeToAttach : department.getEmpleados()) {
                empleadosEmployeeToAttach = em.getReference(empleadosEmployeeToAttach.getClass(), empleadosEmployeeToAttach.getEid());
                attachedEmpleados.add(empleadosEmployeeToAttach);
            }
            department.setEmpleados(attachedEmpleados);
            em.persist(department);
            for (Employee empleadosEmployee : department.getEmpleados()) {
                Department oldDepartmentOfEmpleadosEmployee = empleadosEmployee.getDepartment();
                empleadosEmployee.setDepartment(department);
                empleadosEmployee = em.merge(empleadosEmployee);
                if (oldDepartmentOfEmpleadosEmployee != null) {
                    oldDepartmentOfEmpleadosEmployee.getEmpleados().remove(empleadosEmployee);
                    oldDepartmentOfEmpleadosEmployee = em.merge(oldDepartmentOfEmpleadosEmployee);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Department department) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department persistentDepartment = em.find(Department.class, department.getId());
            List<Employee> empleadosOld = persistentDepartment.getEmpleados();
            List<Employee> empleadosNew = department.getEmpleados();
            List<Employee> attachedEmpleadosNew = new ArrayList<Employee>();
            for (Employee empleadosNewEmployeeToAttach : empleadosNew) {
                empleadosNewEmployeeToAttach = em.getReference(empleadosNewEmployeeToAttach.getClass(), empleadosNewEmployeeToAttach.getEid());
                attachedEmpleadosNew.add(empleadosNewEmployeeToAttach);
            }
            empleadosNew = attachedEmpleadosNew;
            department.setEmpleados(empleadosNew);
            department = em.merge(department);
            for (Employee empleadosOldEmployee : empleadosOld) {
                if (!empleadosNew.contains(empleadosOldEmployee)) {
                    empleadosOldEmployee.setDepartment(null);
                    empleadosOldEmployee = em.merge(empleadosOldEmployee);
                }
            }
            for (Employee empleadosNewEmployee : empleadosNew) {
                if (!empleadosOld.contains(empleadosNewEmployee)) {
                    Department oldDepartmentOfEmpleadosNewEmployee = empleadosNewEmployee.getDepartment();
                    empleadosNewEmployee.setDepartment(department);
                    empleadosNewEmployee = em.merge(empleadosNewEmployee);
                    if (oldDepartmentOfEmpleadosNewEmployee != null && !oldDepartmentOfEmpleadosNewEmployee.equals(department)) {
                        oldDepartmentOfEmpleadosNewEmployee.getEmpleados().remove(empleadosNewEmployee);
                        oldDepartmentOfEmpleadosNewEmployee = em.merge(oldDepartmentOfEmpleadosNewEmployee);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = department.getId();
                if (findDepartment(id) == null) {
                    throw new NonexistentEntityException("The department with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
            }
            List<Employee> empleados = department.getEmpleados();
            for (Employee empleadosEmployee : empleados) {
                empleadosEmployee.setDepartment(null);
                empleadosEmployee = em.merge(empleadosEmployee);
            }
            em.remove(department);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Department.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Department findDepartment(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Department> rt = cq.from(Department.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
