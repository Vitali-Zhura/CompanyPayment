package com.epam.courses.paycom.dao;


import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.stream.Stream;


public abstract class GenericDAOWithJPA<T, ID extends Serializable> {

    public Class<T> persistentClass;

    //This you might want to get injected by the container
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericDAOWithJPA() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public Stream<T> findAll() {
        return (Stream<T>) entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
    }
}
