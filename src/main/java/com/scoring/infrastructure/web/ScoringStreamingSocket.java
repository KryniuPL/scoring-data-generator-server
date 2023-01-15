package com.scoring.infrastructure.web;

import com.scoring.domain.scoring.ScoringCalculatedEvent;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.http.MediaType;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnError;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ServerWebSocket("/api/scoring")
public class ScoringStreamingSocket implements ApplicationEventListener<ScoringCalculatedEvent> {

    @Inject
    private WebSocketBroadcaster broadcaster;

    @Override
    public void onApplicationEvent(ScoringCalculatedEvent event) {
        log.info("New scoring: {}", event);
        broadcaster.broadcastAsync(event, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Received message: {}", message);
    }

    @OnOpen
    public void onOpen() {
       log.info("Websocket opened");
    }

    @OnError
    public void onError(Throwable error) {
        log.error("Websocket error: {}", error.getMessage());
    }

    @OnClose
    public void onClose() {
        log.info("Websocket closed");
    }

}
