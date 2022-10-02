package org.aibles.book_service.repository;

import org.aibles.book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

  boolean existsByName(String name);
}
