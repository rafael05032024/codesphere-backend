package br.com.codesphere.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.SseEventSink;

@ApplicationScoped
public class SSEService {

  private final Map<String, SseEventSink> clients = new ConcurrentHashMap<>();

  public void register(String userId, SseEventSink sink) {
    clients.put(userId, sink);
    System.out.println("Cliente conectado: " + userId);
  }

  public void sendTo(String userId, OutboundSseEvent event) {
    SseEventSink sink = clients.get(userId);

    if (sink == null || sink.isClosed()) {
      clients.remove(userId);
      return;
    }

    sink.send(event);
  }

  public void remove(String userId) {
    clients.remove(userId);
  }
}
