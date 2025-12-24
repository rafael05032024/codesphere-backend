package br.com.codesphere.resources;

import br.com.codesphere.dtos.AuthRequestDTO;
import br.com.codesphere.services.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

  @Inject
  AuthService service;

  @POST
  public Object signIn(@Valid AuthRequestDTO request) {
    return service.signIn(request);
  }

} 
