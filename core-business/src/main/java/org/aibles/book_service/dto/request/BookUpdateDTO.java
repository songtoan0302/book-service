package org.aibles.book_service.dto.request;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import org.aibles.book_service.entity.Book;

public class BookUpdateDTO extends BookCreateDTO{
  @NotBlank
  private String id;

  public BookUpdateDTO() {
  }

  public BookUpdateDTO(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookUpdateDTO that = (BookUpdateDTO) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Book toBook(Book book, BookUpdateDTO bookUpdateDTO) {
    book.setId(bookUpdateDTO.getId());
    book.setName(bookUpdateDTO.getName());
    book.setDescription(bookUpdateDTO.getDescription());
    book.setReleaseAt(bookUpdateDTO.getReleaseAt());
    book.setActive(bookUpdateDTO.getActive());
    return book;
  }
}
