package org.arquillian.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceBase<T> {
    private T result;
    private List<Error> errors;

    public ResourceBase(T result, List<Error> errors) {
        this.result = result;
        this.errors = errors;
    }

    public ResourceBase(T result) {
        this.result = result;
    }

    public ResourceBase(List<Error> errors) {
        this.errors = errors;
    }

    private ResourceBase(ResourceBaseBuilder<T> resourceBaseBuilder) {
        setResult(resourceBaseBuilder.result);
        setErrors(resourceBaseBuilder.errors);
    }

    public static ResourceBaseBuilder resourceBaseBuilder() {
        return new ResourceBaseBuilder();
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public static final class ResourceBaseBuilder<T> {
        private T result;
        private List<Error> errors;

        private ResourceBaseBuilder() {
        }

        public ResourceBaseBuilder result(T result) {
            this.result = result;
            return this;
        }

        public ResourceBaseBuilder error(Error error) {
            if(errors == null){
                errors = new LinkedList<Error>();
            }
            errors.add(error);
            return this;
        }

        public ResourceBaseBuilder error(Integer code, String message, String field) {
            if(errors == null){
                errors = new LinkedList<Error>();
            }
            errors.add(new Error(code, message, field));
            return this;
        }

        public ResourceBase build() {
            return new ResourceBase(this);
        }
    }
}
