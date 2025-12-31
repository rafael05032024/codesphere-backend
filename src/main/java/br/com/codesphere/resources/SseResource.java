package br.com.codesphere.resources;

import br.com.codesphere.services.SSEService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.SseEventSink;

@Path("/events")
@ApplicationScoped
public class SseResource {

  private final SSEService registry;

  public SseResource(SSEService registry) {
    this.registry = registry;
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public void connect(
      @PathParam("userId") String userId,
      @Context SseEventSink sink) {
    registry.register(userId, sink);
  }
}
