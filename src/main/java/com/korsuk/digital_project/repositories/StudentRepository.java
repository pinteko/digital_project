package com.korsuk.digital_project.repositories;

import com.korsuk.digital_project.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAll();

    Optional<Student> findStudentById(Long id);

    Optional<Student> findStudentByName(String name);

    @Query("select s from Student s where s.name = ?1")
    Optional<Student> findByName(String name);

    void deleteStudentById(Long id);

    boolean existsByName(String name);

}
