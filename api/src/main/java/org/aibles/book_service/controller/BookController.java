package org.aibles.book_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book_service.dto.request.BookCreateDTO;
import org.aibles.book_service.dto.request.BookUpdateDTO;
import org.aibles.book_service.dto.response.BookResponseDTO;
import org.aibles.book_service.service.BookService;
import org.aibles.book_service.util.paging.PagingReq;
import org.aibles.book_service.util.paging.PagingRes;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookResponseDTO create(@RequestBody @Validated BookCreateDTO bookCreateDTO) {
    log.info("(create)bookCreateDTO: {}", bookCreateDTO);
    return service.create(bookCreateDTO);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponseDTO update(@PathVariable String id, @RequestBody @Validated BookUpdateDTO bookUpdateDTO) {
    log.info("(update)id: {}, bookUpdateDTO: {}", id, bookUpdateDTO);
    return service.update(id, bookUpdateDTO);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BookResponseDTO get(@PathVariable String id) {
    log.info("(get)id: {}", id);
    return service.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public PagingRes<BookResponseDTO> list(PagingReq pagingReq) {
    log.info("(list)pagingReq: {}", pagingReq);
    return PagingRes.of(service.list(pagingReq));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable String id) {
    log.info("(delete)id :{}", id);
    service.delete(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public void deleteAll() {
    log.info("(deleteAll)");
    service.deleteAll();
  }
}
