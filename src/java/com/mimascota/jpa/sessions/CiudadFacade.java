/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jpa.sessions;

import com.mimascota.jpa.entities.Ciudad;
import com.mimascota.jpa.entities.Ciudad_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author leoandresm
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "VeterinariaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    
    public List<Ciudad> findByNombre(String nombre) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Ciudad> cq = cb.createQuery(Ciudad.class);
        Root<Ciudad> ciudad = cq.from(Ciudad.class);
        cq.where(cb.like(ciudad.get(Ciudad_.nombreCiudad), nombre + "%"));
        TypedQuery<Ciudad> q = getEntityManager().createQuery(cq);
        try {
            return q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }    
    
}
