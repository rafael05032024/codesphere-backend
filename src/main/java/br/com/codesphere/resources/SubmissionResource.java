package br.com.codesphere.resources;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.codesphere.dtos.CreateSubmissionDTO;
import br.com.codesphere.services.SubmissionService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/submission")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class SubmissionResource {

  @Inject
  SubmissionService service;

  @Inject
  JsonWebToken jwt;

  @POST
  public void create(@Valid CreateSubmissionDTO body) {
    JsonNumber claim = jwt.getClaim("userId");
    Long userId = claim.longValue();

    service.create(body, userId);
  }
  
}
