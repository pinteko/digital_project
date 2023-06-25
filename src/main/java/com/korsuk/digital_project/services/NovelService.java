package com.korsuk.digital_project.services;

import com.korsuk.digital_project.converters.NovelConverter;
import com.korsuk.digital_project.dtos.NovelDto;
import com.korsuk.digital_project.dtos.NovelToSave;
import com.korsuk.digital_project.entities.Author;
import com.korsuk.digital_project.entities.Novel;
import com.korsuk.digital_project.exceptions.ExistEntityException;
import com.korsuk.digital_project.exceptions.ResourceNotFoundException;
import com.korsuk.digital_project.repositories.NovelRepository;
import com.korsuk.digital_project.repositories.specification.NovelSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelService {
    private final NovelRepository novelRepository;
    private final AuthorService authorService;

    private final NovelConverter novelConverter;


    public Page<NovelDto> findNovels(Integer p, Double minRating, Double maxRating,
                                  Double minPrice, Double maxPrice, String titlePart, String name, String surname) {

        List<Author> authors = new ArrayList<>();
        if (name != null || surname != null) {
            authors = authorService.findAuthors(name, surname);
        }

        Specification<Novel> spec = Specification.where(null);
        if (minRating != null) {
            spec = spec.and(NovelSpecification.ratingGreaterThanOrEqualTo(minRating));
        }
        if (maxRating != null) {
            spec = spec.and(NovelSpecification.ratingLessThanOrEqualTo(maxRating));
        }
        if (minPrice != null) {
            spec = spec.and(NovelSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(NovelSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(NovelSpecification.titleLike(titlePart));
        }
        if (authors.size() > 0) {
            spec = spec.and(NovelSpecification.authorLike(authors.get(0)));
        }

        spec = spec.and(NovelSpecification.orderById());

        Page<Novel> pageNovels = novelRepository.findAll(spec, PageRequest.of(p - 1, 5));

        return pageNovels.map(this::convertToNovelDto);
    }

    private NovelDto convertToNovelDto(Novel o) {
        return novelConverter.entityToDto(o);
    }

    public NovelDto getNovelByIdDto(Long id) {
       Novel novel = novelRepository.findNovelById(id).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + id));
        return novelConverter.entityToDto(novel);
    }

    public void deleteNovelById(Long id) {
        novelRepository.deleteById(id);
    }

    @Transactional
    public void changeRating(Long id, Double delta) {
        Novel novel = novelRepository.findNovelById(id).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + id));
        novel.setRating(novel.getRating() + delta);
        novelRepository.save(novel);
    }

    public NovelDto save(NovelToSave newNovel) {
        if (novelRepository.existsNovelByTitle(newNovel.getTitle())) {
            throw new ExistEntityException("This novel is already in library");
        } else {
            Author author = new Author();
            author.setName(newNovel.getAuthorName());
            author.setSurname(newNovel.getAuthorSurname());
            Author authorSaved = authorService.save(author);
            Novel novel = new Novel();
            novel.setTitle(newNovel.getTitle());
            novel.setAuthor(authorSaved);
            novel.setRating(newNovel.getRating());
            novel.setPrice(newNovel.getPrice());
            Novel novelSaved = novelRepository.saveAndFlush(novel);
            return novelConverter.entityToDto(novelSaved);
        }
    }

    @Transactional
    public NovelDto update(NovelToSave updateNovel) {
           Novel novelToUpdate = novelRepository.findNovelByTitle(updateNovel.getTitle());
           novelToUpdate.setPrice(updateNovel.getPrice());
           novelToUpdate.setRating(updateNovel.getRating());
           novelToUpdate.setUpdatedAt(LocalDateTime.now());
           return novelConverter.entityToDto(novelRepository.saveAndFlush(novelToUpdate));

    }
}
