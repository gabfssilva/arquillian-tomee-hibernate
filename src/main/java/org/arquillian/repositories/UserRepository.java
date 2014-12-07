package org.arquillian.repositories;

import org.arquillian.entities.User;
import org.arquillian.qualifiers.CustomEntityManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Stateless
public class UserRepository extends Repository<User, Long> {
    @Inject @CustomEntityManager
    private EntityManager entityManager;

    @Override
    protected EntityManager entityManager() {
        return entityManager;
    }
}
