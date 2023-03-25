package com.dungnguyen.reactivewebfluxdemo.restcontrollerstyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping("/sse")
public class ServerSentEventController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> streamFlux() {
    return Flux.interval(Duration.ofSeconds(1))
        .map(sequence -> "Flux - " + LocalTime.now().toString());
  }

  @GetMapping("/stream-sse")
  public Flux<ServerSentEvent<String>> streamEvents() {
    Thread currentThread = Thread.currentThread();
    return Flux.interval(Duration.ofSeconds(1))
        .map(
            sequence ->
                ServerSentEvent.<String>builder()
                    .id(String.valueOf(sequence))
                    .event("periodic-event")
                    .comment("current-thread: " + currentThread.getName())
                    .data("SSE - " + LocalTime.now().toString())
                    .build())
        .doOnSubscribe(subscription -> logger.info("[ON_SUBSCRIBE] {}", subscription.toString()))
        .doOnCancel(() -> logger.info("[ON_CANCEL] thread: {}", currentThread.getName()))
        .doOnComplete(() -> logger.info("[ON_COMPLETE] thread: {}", currentThread.getName()))
        .doOnError(e -> logger.info("[ON_ERROR= {}]", e));
  }
}
