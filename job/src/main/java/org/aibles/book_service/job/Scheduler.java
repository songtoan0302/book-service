package org.aibles.book_service.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book_service.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class Scheduler {

  private final BookService service;
  private static final String CRON_EXPRESSION = "0 0 0 * * ?";

  @Scheduled(cron = CRON_EXPRESSION)
  public void releaseBook() {
    log.info("(releaseBook)");
    service.releaseBook();
  }
}
