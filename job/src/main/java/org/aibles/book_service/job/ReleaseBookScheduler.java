package org.aibles.book_service.job;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book_service.entity.Book;
import org.aibles.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReleaseBookScheduler {

  private final BookService service;

  /**
   * biến này để xác định job này bật hay tắt đặt tên biến môi trường theo quy tắc
   * appilcation.{tên_job}.{chức_năng_job}.tên biến
   */
  @Value("${application.book_scheduler.release_book.enable:true}")
  private Boolean enable;

  @Value("${application.book_scheduler.release_book.size:1000}")
  private Integer size;

  @Scheduled(cron = "${application.book_scheduler.release_book.cron:0 0 16 * * ?}")
  public void execute() {
    log.info("(execute)enable: {}", enable);
    try {
      if (Boolean.FALSE.equals(enable)) {
        return;
      }

      int page = 0;
      while (true) {
        List<Book> books = service.find(Instant.now(), page, size).getContent();
        var updatedBooks = books.parallelStream()
            .map(book -> {
              book.setActive(true);
              return book;
            }).collect(Collectors.toList());
        service.createAll(updatedBooks);

        if (books.size() < size) {
          break;
        }
        page++;
      }
    } catch (Exception ex) {
      log.error("(execute)ex: {}", ex.getMessage());
    }
  }

}
