/**
 * @author Zain I.
 **/

package com.poc.liquibase.repository;

import com.poc.liquibase.entity.TblBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<TblBook, Long> {
    List<TblBook> findByPublished(boolean published);

    List<TblBook> findByTitle(String title);

    List<TblBook> findByTitleContaining(String title);

    @Query(value = "SELECT coalesce(max(id), 1) FROM TblBook")
    Long findMaxId();
}