package com.freetech.products.productservice.infraestructure.adapters.out;

import com.freetech.products.productservice.infraestructure.ports.out.EntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class PostgresRepository implements EntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public <T> T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public <T> T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public <K, T> T getById(K id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    @Transactional
    @Override
    public <T> T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }
}
