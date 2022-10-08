package org.aibles.book_service.service;

import java.time.Instant;
import java.util.List;
import org.aibles.book_service.dto.request.BookCreateDTO;
import org.aibles.book_service.dto.request.BookUpdateDTO;
import org.aibles.book_service.dto.response.BookResponseDTO;
import org.aibles.book_service.entity.Book;
import org.aibles.book_service.util.paging.PagingReq;
import org.springframework.data.domain.Page;

public interface BookService {

  /**
   * create a new book
   *
   * @param bookCreateDTO
   * @return BookResponseDTO
   */
  BookResponseDTO create(BookCreateDTO bookCreateDTO);

  /**
   * create a new list book
   *
   * @param books
   */
  void createAll(List<Book> books);

  /**
   * update book by id
   *
   * @param bookUpdateDTO
   * @return BookResponseDTO
   */
  BookResponseDTO update(String id, BookUpdateDTO bookUpdateDTO);

  /**
   * @param id
   * @return BookResponseDTO
   */
  BookResponseDTO get(String id);

  /**
   * get all book and paging
   *
   * @return Page<BookResponseDTO>
   */
  Page<BookResponseDTO> list(PagingReq pagingReq);

  /**
   * delete book by id
   *
   * @param id
   */
  void delete(String id);

  /**
   * delete all book
   */
  void deleteAll();

  /**
   * release book follow condition
   */
  void releaseBook();

  /**
   *
   * @param releaseAt
   * @param pageNum
   * @param pageSize
   * @return
   */
  Page<Book> find(Instant releaseAt,int pageNum, int pageSize);
}
