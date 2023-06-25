package com.korsuk.digital_project.repositories;

import com.korsuk.digital_project.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where g.title = ?1")
    Group findByTitle(String title);

    boolean existsByTitle(String title);
}
