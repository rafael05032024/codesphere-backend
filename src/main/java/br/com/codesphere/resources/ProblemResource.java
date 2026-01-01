package br.com.codesphere.resources;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.codesphere.dtos.CreateProblemDTO;
import br.com.codesphere.dtos.ProblemDetailDTO;
import br.com.codesphere.dtos.ProblemListDTO;
import br.com.codesphere.services.ProblemService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.json.JsonNumber;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/problem")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
public class ProblemResource {

  @Inject
  ProblemService service;

  @Inject
  JsonWebToken jwt;

  @POST
  public void create(@Valid CreateProblemDTO body) {
    JsonNumber claim = jwt.getClaim("userId");
    Long userId = claim.longValue();

    service.create(body, userId);
  }

  @GET
  public ProblemListDTO list(@QueryParam("categoryId") long categoryId) {
    return service.listByCategory(categoryId);
  }

  @GET
  @Path("/search")
  public ProblemListDTO search(@QueryParam("term") String term) {
    return service.search(term);
  }

  @GET
  @Path("/{id}")
  public ProblemDetailDTO detail(@PathParam("id") long id) {
    return service.findById(id);
  }
}
