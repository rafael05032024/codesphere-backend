package br.com.codesphere.resources;

import java.net.URI;

import br.com.codesphere.dtos.AuthResponseDTO;
import br.com.codesphere.integration.github.dtos.GitHubExchangeCodeDTO;
import br.com.codesphere.services.GitHubAuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
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
    String url = state;

    String cookieHeader = "access_token=" + jwt + "; Path=/" + "; HttpOnly"
        + "; SameSite=Lax";
    return Response.seeOther(URI.create(url)).header(HttpHeaders.SET_COOKIE, cookieHeader).build();
  }

  @POST
  @Path("/exchange")
  public AuthResponseDTO exchange(GitHubExchangeCodeDTO body) {
    String code = body.code;
    String jwt = gitHubAuthService.auth(code);

    return new AuthResponseDTO(jwt);
  }

}
