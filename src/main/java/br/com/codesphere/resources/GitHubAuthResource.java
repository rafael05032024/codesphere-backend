package br.com.codesphere.resources;

import java.net.URI;
import java.util.Map;

import br.com.codesphere.services.GitHubAuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/auth/github")
public class GitHubAuthResource {

  @Inject
  GitHubAuthService gitHubAuthService;

  @GET
  @Path("/login")
  public Response login() {
    return Response.seeOther(URI.create(gitHubAuthService.buildLoginUrl())).build();
  }

  @GET
  @Path("/callback")
  public Response callback(@QueryParam("code") String code) {
    String jwt = gitHubAuthService.auth(code);

    return Response.ok(Map.of("token", jwt)).build();
  }

}
