package org.aibles.book_service.entity;


import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.aibles.book_service.validation.ModelValidator;

@Entity
@Table(name = "book")
public class Book extends ModelValidator<Book> {

  @Id
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

  public Book() {
  }

  public Book(String id, String name, String description, Boolean isActive, Instant releaseAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.isActive = isActive;
    this.releaseAt = releaseAt;
  }

  @PrePersist
  public void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : id;
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
    Book book = (Book) o;
    return Objects.equals(id, book.id) && Objects.equals(name, book.name)
        && Objects.equals(description, book.description) && Objects.equals(
        isActive, book.isActive) && Objects.equals(releaseAt, book.releaseAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, isActive, releaseAt);
  }
}
