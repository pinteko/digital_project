package com.korsuk.digital_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @JoinTable(name = "novel_readers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id"))
    private List<Novel> novels;


    public Student(Long id, String name, Integer age, Group group) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.group = group;
    }
}
