package org.arquillian.interceptors;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.method.MethodValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.validation.Validation;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class MethodValidatorProducer {

    @Produces
    @ApplicationScoped
    public MethodValidator methodValidator() {
        MethodValidator methodValidator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory()
                .getValidator()
                .unwrap(MethodValidator.class);

        return methodValidator;
    }
}
