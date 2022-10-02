package org.aibles.book_service.configuration;

import org.aibles.book_service.job.Scheduler;
import org.aibles.book_service.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class JobConfiguration{

  @Bean
  public Scheduler scheduler(BookService service) {
    return new Scheduler(service);
  }

}
