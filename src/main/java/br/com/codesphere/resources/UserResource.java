package br.com.codesphere.resources;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.codesphere.dtos.UserProfileDTO;
import br.com.codesphere.services.UserService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  UserService service;

  @Inject
  JsonWebToken jwt;

  @GET
  @Path("/me")
  public UserProfileDTO getProfile() {
    JsonNumber claim = jwt.getClaim("userId");
    Long userId = claim.longValue();

    return service.getById(userId);
  }

}
