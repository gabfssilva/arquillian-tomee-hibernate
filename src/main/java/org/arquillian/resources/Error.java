package org.arquillian.resources;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Error {
    private Integer code;
    private String field;
    private String message;

    public Error(Integer code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public Error() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
