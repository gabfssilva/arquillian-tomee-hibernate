package org.arquillian.exceptions;

import org.hibernate.validator.method.MethodConstraintViolation;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class DomainValidationException extends RuntimeException{
    private Class<?> targetClass;
    private Method targetMethod;
    private Set<MethodConstraintViolation<Object>> constraintViolations;

    public DomainValidationException(Class<?> targetClass, Method targetMethod, Set<MethodConstraintViolation<Object>> constraintViolations) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.constraintViolations = constraintViolations;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Set<MethodConstraintViolation<Object>> getConstraintViolations() {
        return constraintViolations;
    }
}
