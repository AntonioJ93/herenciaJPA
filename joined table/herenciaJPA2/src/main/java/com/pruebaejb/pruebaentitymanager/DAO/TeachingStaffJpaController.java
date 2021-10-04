/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager.DAO;

import com.pruebaejb.pruebaentitymanager.DAO.exceptions.NonexistentEntityException;
import com.pruebaejb.pruebaentitymanager.TeachingStaff;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author pryet
 */
public class TeachingStaffJpaController implements Serializable {

    public TeachingStaffJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TeachingStaff teachingStaff) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(teachingStaff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TeachingStaff teachingStaff) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            teachingStaff = em.merge(teachingStaff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = teachingStaff.getSid();
                if (findTeachingStaff(id) == null) {
                    throw new NonexistentEntityException("The teachingStaff with id " + id + " no longer exists.");
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
            TeachingStaff teachingStaff;
            try {
                teachingStaff = em.getReference(TeachingStaff.class, id);
                teachingStaff.getSid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The teachingStaff with id " + id + " no longer exists.", enfe);
            }
            em.remove(teachingStaff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TeachingStaff> findTeachingStaffEntities() {
        return findTeachingStaffEntities(true, -1, -1);
    }

    public List<TeachingStaff> findTeachingStaffEntities(int maxResults, int firstResult) {
        return findTeachingStaffEntities(false, maxResults, firstResult);
    }

    private List<TeachingStaff> findTeachingStaffEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TeachingStaff.class));
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

    public TeachingStaff findTeachingStaff(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TeachingStaff.class, id);
        } finally {
            em.close();
        }
    }

    public int getTeachingStaffCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TeachingStaff> rt = cq.from(TeachingStaff.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
