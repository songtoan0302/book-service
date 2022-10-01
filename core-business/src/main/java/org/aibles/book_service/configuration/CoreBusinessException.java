package org.aibles.book_service.configuration;

import org.aibles.book_service.repository.BookRepository;
import org.aibles.book_service.service.BookService;
import org.aibles.book_service.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan( basePackages = {"org.aibles.book_service.repository"})
@EnableJpaRepositories(basePackages = {"org.aibles.book_service.repository"})
public class CoreBusinessException {

  @Bean
  public BookService bookService(BookRepository repository) {
    return new BookServiceImpl(repository);
  }
}
