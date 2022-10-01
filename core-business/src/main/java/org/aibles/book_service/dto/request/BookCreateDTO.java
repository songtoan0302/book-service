package org.aibles.book_service.dto.request;

import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.aibles.book_service.entity.Book;
import org.aibles.book_service.validation.ModelValidator;

public class BookCreateDTO extends ModelValidator<BookCreateDTO> {

  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotNull
  private Boolean isActive;
  @NotNull
  private Instant releaseAt;

  public BookCreateDTO() {
  }

  public BookCreateDTO(String name, String description, Boolean isActive, Instant releaseAt) {
    this.name = name;
    this.description = description;
    this.isActive = isActive;
    this.releaseAt = releaseAt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Instant getReleaseAt() {
    return releaseAt;
  }

  public void setReleaseAt(Instant releaseAt) {
    this.releaseAt = releaseAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookCreateDTO that = (BookCreateDTO) o;
    return Objects.equals(name, that.name) && Objects.equals(description,
        that.description) && Objects.equals(isActive, that.isActive)
        && Objects.equals(releaseAt, that.releaseAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, isActive, releaseAt);
  }

  public Book toBook(BookCreateDTO bookCreateDTO) {
    var book = new Book();
    book.setName(bookCreateDTO.getName());
    book.setDescription(bookCreateDTO.getDescription());
    book.setReleaseAt(bookCreateDTO.getReleaseAt());
    book.setActive(bookCreateDTO.getActive());
    return book;
  }
}
