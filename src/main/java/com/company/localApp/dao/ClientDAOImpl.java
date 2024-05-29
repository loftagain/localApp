package com.company.localApp.dao;

import com.company.localApp.Clientele;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientInterface{

    private EntityManager entityManager;
@Autowired
    public ClientDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional //cause an update
    public void save(Clientele theClientele) {
        entityManager.persist(theClientele);
    }

    @Override
    public Clientele findById(Integer id) {
        return entityManager.find(Clientele.class,id); //entity class and primary key
    }

    @Override
    public List<Clientele> findAll() {
        TypedQuery<Clientele> theQuery=entityManager.createQuery("FROM Clientele order by bank_account",Clientele.class);
        return theQuery.getResultList();
    }
}
