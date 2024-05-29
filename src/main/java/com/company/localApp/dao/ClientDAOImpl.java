package com.company.localApp.dao;

import com.company.localApp.Clientele;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
