package me.itzg.trying.logtofluentd;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class TrySpringBootLoggingToFluentdApplication {

  public static void main(String[] args) {
    SpringApplication.run(TrySpringBootLoggingToFluentdApplication.class, args);
  }

  @Scheduled(fixedRate = 1000L)
  public void logTheTime() {
    log.info("The current time is {}", new Date());
  }

  @Scheduled(fixedRate = 10000L)
  public void logAnException() {
    try {
      buggy();
    } catch (Exception e) {
      log.warn("An expected bug happened", e);
    }
  }

  private void buggy() {
    throw new IllegalStateException("I am a bug");
  }
}
