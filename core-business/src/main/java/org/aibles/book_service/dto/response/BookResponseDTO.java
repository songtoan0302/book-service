package org.aibles.book_service.dto.response;

import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.aibles.book_service.entity.Book;
import org.aibles.book_service.validation.ModelValidator;

public class BookResponseDTO extends ModelValidator<BookResponseDTO> {

  @NotBlank
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotNull
  private Boolean isActive;
  @NotNull
  private Instant releaseAt;

  public BookResponseDTO() {
  }

  public BookResponseDTO(String id, String name, String description, Boolean isActive,
      Instant releaseAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.isActive = isActive;
    this.releaseAt = releaseAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
    BookResponseDTO that = (BookResponseDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(description, that.description) && Objects.equals(
        isActive, that.isActive) && Objects.equals(releaseAt, that.releaseAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, isActive, releaseAt);
  }

  public BookResponseDTO toBookResponseDTO(Book book) {
    this.id = book.getId();
    this.name = book.getName();
    this.description = book.getDescription();
    this.isActive = book.getActive();
    this.releaseAt = book.getReleaseAt();
    return this;
  }
}
