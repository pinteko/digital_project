package com.korsuk.digital_project.services;

import com.korsuk.digital_project.entities.Author;
import com.korsuk.digital_project.exceptions.ExistEntityException;
import com.korsuk.digital_project.exceptions.ResourceNotFoundException;
import com.korsuk.digital_project.repositories.AuthorRepository;
import com.korsuk.digital_project.repositories.specification.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findById(Long id) {
       return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }

    public Author findBySurname(String surname) {
        return authorRepository.findAuthorBySurname(surname).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }
    public List<Author> findAuthors(String name, String surname) {
        Specification<Author> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(AuthorSpecification.nameLike(name));
        }
        if (surname != null) {
            spec = spec.and(AuthorSpecification.surnameLike(surname));
        }
       return authorRepository.findAll(spec);
    }

    @Transactional
    public Author save(Author author) {
        Author returnAuthor = authorRepository.findAuthorByNameAndSurname(author.getName(), author.getSurname());
        return Objects.requireNonNullElseGet(returnAuthor, () -> authorRepository.save(author));
    }

    public Author update (Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
