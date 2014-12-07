package org.arquillian.interceptors;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@InterceptorBinding
@Target({TYPE, METHOD })
@Retention(RUNTIME)
public @interface BeanValidation {
}
