package ars.cs.miu.edu.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public  class Person {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @Column(unique=true)
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Role role;
    public Person(){}

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
}
