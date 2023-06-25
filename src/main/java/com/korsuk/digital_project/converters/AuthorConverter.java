package com.korsuk.digital_project.converters;

import com.korsuk.digital_project.dtos.AuthorDto;
import com.korsuk.digital_project.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public Author dtoToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getSurname());
    }

    public AuthorDto entityToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getSurname());
    }
}
