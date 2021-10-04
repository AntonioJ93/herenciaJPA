/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebaejb.pruebaentitymanager.DAO;

import com.pruebaejb.pruebaentitymanager.DAO.exceptions.NonexistentEntityException;
import com.pruebaejb.pruebaentitymanager.NonTeachingStaff;
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
public class NonTeachingStaffJpaController implements Serializable {

    public NonTeachingStaffJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NonTeachingStaff nonTeachingStaff) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nonTeachingStaff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NonTeachingStaff nonTeachingStaff) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nonTeachingStaff = em.merge(nonTeachingStaff);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = nonTeachingStaff.getSid();
                if (findNonTeachingStaff(id) == null) {
                    throw new NonexistentEntityException("The nonTeachingStaff with id " + id + " no longer exists.");
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
            NonTeachingStaff nonTeachingStaff;
            try {
                nonTeachingStaff = em.getReference(NonTeachingStaff.class, id);
                nonTeachingStaff.getSid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nonTeachingStaff with id " + id + " no longer exists.", enfe);
            }
            em.remove(nonTeachingStaff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NonTeachingStaff> findNonTeachingStaffEntities() {
        return findNonTeachingStaffEntities(true, -1, -1);
    }

    public List<NonTeachingStaff> findNonTeachingStaffEntities(int maxResults, int firstResult) {
        return findNonTeachingStaffEntities(false, maxResults, firstResult);
    }

    private List<NonTeachingStaff> findNonTeachingStaffEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NonTeachingStaff.class));
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

    public NonTeachingStaff findNonTeachingStaff(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NonTeachingStaff.class, id);
        } finally {
            em.close();
        }
    }

    public int getNonTeachingStaffCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NonTeachingStaff> rt = cq.from(NonTeachingStaff.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
