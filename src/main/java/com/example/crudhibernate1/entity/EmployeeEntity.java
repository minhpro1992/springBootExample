package com.example.crudhibernate1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TBL_EMPLOYEES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email", nullable = false, length = 200)
    private String email;

    @Column(name="gender")
    private String gender;

    @Column(name="age")
    private Integer age;
}
