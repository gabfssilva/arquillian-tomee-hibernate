package org.arquillian.repositories;

import org.arquillian.entities.Person;
import org.arquillian.qualifiers.CustomEntityManager;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class PersonRepository extends Repository<Person, Long> {
    @Inject @CustomEntityManager
    private EntityManager entityManager;

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
