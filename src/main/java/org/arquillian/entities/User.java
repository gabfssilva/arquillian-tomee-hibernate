package org.arquillian.entities;


import br.com.caelum.stella.bean.validation.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Field name cannot be null")
    private String name;
    @NotNull(message = "Field occupation cannot be null")
    private String occupation;
    @NotNull(message = "Field cpf cannot be null")
    @CPF
    private String cpf;

    public User(Long id, String name, String occupation, String cpf) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.cpf = cpf;
    }

    public User() {
    }

    private User(UserBuilder userBuilder) {
        setId(userBuilder.id);
        setName(userBuilder.name);
        setOccupation(userBuilder.occupation);
        setCpf(userBuilder.cpf);
    }

    public static UserBuilder newBuilder() {
        return new UserBuilder();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }


    public static final class UserBuilder {
        private Long id;
        private String name;
        private String occupation;
        private String cpf;

        private UserBuilder() {
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public UserBuilder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
