package org.arquillian.repositories;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public abstract class Repository<T, K> {
    public T save(T entity){
        return entityManager().merge(entity);
    }

    public void delete(K key){
        entityManager().remove(fetch(key));
    }

    public T fetch(K key) {
        return entityManager().find(getTypeClass(), key);
    }

    public List<T> findAll(){
        return entityManager().createQuery("SELECT entity FROM " + getTypeClass().getSimpleName() + " entity").getResultList();
    }

    public Long size(){
        return (Long) entityManager().createQuery("SELECT count(entity) FROM " + getTypeClass().getSimpleName() + " entity").getSingleResult();
    }

    private Class<T> getTypeClass() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected abstract EntityManager entityManager();
}
