package com.korsuk.digital_project.repositories.specification;

import com.korsuk.digital_project.entities.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {

    public static Specification<Author> nameLike(String namePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart)));
    }

    public static Specification<Author> surnameLike(String surnamePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), String.format("%%%s%%", surnamePart)));
    }
}
