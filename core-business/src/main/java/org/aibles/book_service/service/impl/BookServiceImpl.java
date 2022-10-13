package org.aibles.book_service.service.impl;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book_service.dto.request.BookCreateDTO;
import org.aibles.book_service.dto.request.BookUpdateDTO;
import org.aibles.book_service.dto.response.BookResponseDTO;
import org.aibles.book_service.entity.Book;
import org.aibles.book_service.repository.BookRepository;
import org.aibles.book_service.service.BookService;
import org.aibles.book_service.util.paging.PagingReq;
import org.aibles.core_exception.exception.ConflictException;
import org.aibles.core_exception.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository repository;

  @Override
  @Transactional
  public BookResponseDTO create(BookCreateDTO bookCreateDTO) {
    log.info("(create)bookCreateDTO: {}", bookCreateDTO);

    checkNameConflict(bookCreateDTO.getName());

    var book = bookCreateDTO.toBook(bookCreateDTO);
    book = repository.save(book);
    book.validate();

    var response = new BookResponseDTO();
    response.toBookResponseDTO(book);
    response.validate();
    return response;
  }

  @Override
  public void createAll(List<Book> books) {
    repository.saveAll(books);
  }

  @Override
  @Transactional
  public BookResponseDTO update(String id, BookUpdateDTO bookUpdateDTO) {
    log.info("(update)id: {}, bookUpdateDTO: {}", id, bookUpdateDTO);
    checkNameConflict(bookUpdateDTO.getName());

    var book = repository.findById(id)
        .orElseThrow(() -> {
          log.error("(update)id: {} ----> NOT_FOUND_EXCEPTION", id);
          throw new NotFoundException(id);
        });
    book = bookUpdateDTO.toBook(book, bookUpdateDTO);
    book.validate();
    book = repository.save(book);

    var response = new BookResponseDTO();
    response.toBookResponseDTO(book);
    response.validate();
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public BookResponseDTO get(String id) {
    log.info("(get)id: {}", id);

    var book = repository.findById(id)
        .orElseThrow(() -> {
          log.error("(get)id: {} ----> NOT_FOUND_EXCEPTION", id);
          throw new NotFoundException(id);
        });

    var response = new BookResponseDTO();
    response.toBookResponseDTO(book);

    return response;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BookResponseDTO> list(PagingReq pagingReq) {
    log.info("(list)pagingReq: {}", pagingReq);
    var bookPage = repository.findAll(pagingReq.makePageable());
    return  mapPage(bookPage);

  }

  @Override
  @Transactional
  public void delete(String id) {
    log.info("(delete)id: {}", id);
    if (repository.existsById(id)) {
      log.error("(delete)id : {} ----> NOT_FOUND_EXCEPTION", id);
      throw new NotFoundException(id);
    }

    repository.deleteById(id);

  }

  @Override
  @Transactional
  public void deleteAll() {
    log.info("(deleteAll)");
    repository.deleteAll();
  }

  @Override
  public void releaseBook() {
    repository.releaseBook();
  }

  @Override
  public Page<Book> find(Instant releaseAt, int pageNum, int pageSize) {
    var pageable = PageRequest.of(pageNum, pageSize);
    return repository.findByReleaseAt(releaseAt,pageable);
  }

  private void checkNameConflict(String name) {
    if (repository.existsByName(name)) {
      log.error("(checkNameConflict)name: {} ----> CONFLICT_EXCEPTION", name);
      throw new ConflictException("Name : '" + name + "' existed!");
    }
  }

  private Page<BookResponseDTO> mapPage(Page<Book> inputData) {
    return inputData.map(book -> {
      var bookResponseDTO = new BookResponseDTO();
      bookResponseDTO = bookResponseDTO.toBookResponseDTO(book);
      bookResponseDTO.validate();

      return bookResponseDTO;
    });
  }
}
