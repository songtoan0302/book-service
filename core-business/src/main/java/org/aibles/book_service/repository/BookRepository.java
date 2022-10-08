package org.aibles.book_service.repository;

import java.time.Instant;
import java.util.List;
import org.aibles.book_service.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

  boolean existsByName(String name);

  @Query(value = "UPDATE book SET book.is_active = 1 WHERE book.release_at > curdate()", nativeQuery = true)
  void releaseBook();

  Page<Book> findByReleaseAt(Instant releaseAt, Pageable pageable);
}
