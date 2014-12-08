package org.arquillian.mappers;

import org.arquillian.exceptions.DomainValidationException;
import org.hibernate.validator.method.MethodConstraintViolation;

import org.arquillian.resources.ResourceBase;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Provider
public class DomainValidationExceptionMapper implements ExceptionMapper<DomainValidationException>{
    @Override
    public Response toResponse(DomainValidationException exception) {
        ResourceBase.ResourceBaseBuilder<?> resourseBaseBuilder = ResourceBase.resourceBaseBuilder();

        for (MethodConstraintViolation<Object> violation : exception.getConstraintViolations()) {
            resourseBaseBuilder.error(null, violation.getParameterName(), violation.getMessage());
        }

        return Response
                    .status(400)
                    .entity(resourseBaseBuilder.build())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
    }
}
