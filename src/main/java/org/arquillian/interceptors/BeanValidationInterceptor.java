package org.arquillian.interceptors;

import org.arquillian.exceptions.DomainValidationException;
import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodValidator;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Set;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Interceptor
@BeanValidation
public class BeanValidationInterceptor {

    @Inject
    private MethodValidator methodValidator;

    @AroundInvoke
    public Object interceptAndValidate(InvocationContext invocationContext) throws Exception {
        Set<MethodConstraintViolation<Object>> constraintViolations = methodValidator.validateAllParameters(invocationContext.getTarget(), invocationContext.getMethod(), invocationContext.getParameters());

        if(constraintViolations.isEmpty()){
            return invocationContext.proceed();
        }

        throw new DomainValidationException(invocationContext.getMethod().getDeclaringClass(), invocationContext.getMethod(), constraintViolations);
    }
}
