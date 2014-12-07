package org.arquillian.converters;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public interface Converter<T, R> {
    R convert(T object);
}
