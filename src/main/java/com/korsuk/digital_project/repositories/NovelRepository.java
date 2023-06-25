package com.korsuk.digital_project.repositories;

import com.korsuk.digital_project.entities.Author;
import com.korsuk.digital_project.entities.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long>, JpaSpecificationExecutor<Novel> {

    @Query("Select n from Novel n order by n.id")
    List<Novel> findAll();

    Novel findNovelByAuthor(Author author);

    Optional<Novel> findNovelById(Long id);

    Novel findNovelByTitle(String title);

    void deleteById(Long id);
    boolean existsNovelByTitle(String title);

}
