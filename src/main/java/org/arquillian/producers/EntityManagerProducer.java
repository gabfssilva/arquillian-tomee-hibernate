package org.arquillian.producers;

import org.arquillian.qualifiers.CustomEntityManager;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class EntityManagerProducer {
    @Produces
    @CustomEntityManager
    @PersistenceContext(unitName = "custom-persistent-unit")
    private EntityManager entityManager;
}
