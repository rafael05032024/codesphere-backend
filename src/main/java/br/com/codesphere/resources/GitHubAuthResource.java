package br.com.codesphere.resources;

import java.net.URI;

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
  public Response login(@QueryParam("redirect") String redirect) {
    return Response.seeOther(URI.create(gitHubAuthService.buildLoginUrl(redirect))).build();
  }

  @GET
  @Path("/callback")
  public Response callback(@QueryParam("code") String code, @QueryParam("state") String state) {
    String jwt = gitHubAuthService.auth(code);
    String url = state + "?token=" + jwt;

    return Response.seeOther(URI.create(url)).build();
  }

}
