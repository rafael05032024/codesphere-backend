package br.com.codesphere.resources;

import java.util.List;

import br.com.codesphere.dtos.LanguageDTO;
import br.com.codesphere.services.LanguageService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/language")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class LanguageResource {

  @Inject
  LanguageService service;

  @GET
  public List<LanguageDTO> list() {
    return service.list();
  }
}
