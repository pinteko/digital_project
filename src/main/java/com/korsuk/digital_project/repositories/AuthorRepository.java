package com.korsuk.digital_project.repositories;

import com.korsuk.digital_project.entities.Author;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorBySurname(String surname);

    Author findAuthorByNameAndSurname(String name, String surname);

    List<Author> findAll(Specification<Author> spec);
}
