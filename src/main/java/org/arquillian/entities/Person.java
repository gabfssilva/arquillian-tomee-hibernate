package org.arquillian.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Entity
@Table(name = "people")
public class Person {
    @Id @GeneratedValue
    private Long id;
    @NotNull(message = "Field name cannot be null")
    private String name;
    @NotNull(message = "Field occupation cannot be null")
    private String occupation;

    public Person(Long id, String name, String occupation) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
    }

    public Person(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }

    public Person() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
