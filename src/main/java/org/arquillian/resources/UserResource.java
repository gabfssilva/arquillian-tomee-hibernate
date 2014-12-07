package org.arquillian.resources;

import br.com.caelum.stella.bean.validation.CPF;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */

public class UserResource {
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Field name cannot be null")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Field occupation cannot be null")
    @JsonProperty("occupation")
    private String occupation;

    @NotNull(message = "Field cpf cannot be null")
    @CPF
    @JsonProperty("cpf")
    private String cpf;

    public UserResource(Long id, String name, String occupation, String cpf) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.cpf = cpf;
    }

    public UserResource() {
    }

    private UserResource(UserResourceBuilder userResourceBuilder) {
        setId(userResourceBuilder.id);
        setName(userResourceBuilder.name);
        setOccupation(userResourceBuilder.occupation);
        setCpf(userResourceBuilder.cpf);
    }

    public static UserResourceBuilder newBuilder() {
        return new UserResourceBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "UserResource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public static final class UserResourceBuilder {
        private Long id;
        private String name;
        private String occupation;
        private String cpf;

        private UserResourceBuilder() {
        }

        public UserResourceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserResourceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserResourceBuilder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public UserResourceBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public UserResource build() {
            return new UserResource(this);
        }
    }
}
