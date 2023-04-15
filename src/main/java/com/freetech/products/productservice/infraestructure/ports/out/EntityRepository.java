package com.freetech.products.productservice.infraestructure.ports.out;

public interface EntityRepository {
    <T> T save(T entity);
    <T> T update(T entity);
    <K,T> T getById(K id, Class<T> clazz);
    <T> T delete(T entity);
}
