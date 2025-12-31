package br.com.codesphere.resources;

import br.com.codesphere.services.SSEService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;

@Path("/notify")
@ApplicationScoped
public class NotifyResource {

  @Context
  Sse sse;

  private final SSEService registry;

  public NotifyResource(SSEService registry) {
    this.registry = registry;
  }

  @GET
  @Path("/{clientId}")
  public void notifyClient(
      @PathParam("clientId") String clientId) {
    OutboundSseEvent event = sse.newEventBuilder()
        .name("private")
        .data(String.class, "CHULÉ")
        .build();

    registry.sendTo(clientId, event);
  }
}
