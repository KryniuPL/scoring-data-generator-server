package com.scoring.infrastructure.web;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.validation.Validated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Controller("/api/stop/{threadId}")
public class StopController {

    @Get
    public void stop(@PathVariable String threadId) {
        Thread threadToStop = Thread.getAllStackTraces().keySet()
                .stream()
                .filter(thread -> thread.getName().equals(threadId))
                .findFirst()
                .orElseThrow(() -> new HttpStatusException(HttpStatus.NOT_FOUND, "Producer %s not found".formatted(threadId)));

        log.info("Thread {} successfully stopped", threadId);
        threadToStop.interrupt();
    }

}
