package br.com.codesphere.resources;

import br.com.codesphere.dtos.CreateProblemDTO;
import br.com.codesphere.dtos.ProblemListDTO;
import br.com.codesphere.services.ProblemService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/problem")
@Produces(MediaType.APPLICATION_JSON)
public class ProblemResource {

  @Inject
  ProblemService service;

  @POST
  public void create(@Valid CreateProblemDTO body) {
    service.create(body);
  }

  @GET
  public ProblemListDTO list(@QueryParam("categoryId") long categoryId) {
    return service.listByCategory(categoryId);
  }
}
