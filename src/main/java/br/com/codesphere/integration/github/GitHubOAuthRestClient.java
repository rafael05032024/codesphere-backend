package br.com.codesphere.integration.github;

import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.codesphere.integration.github.dtos.GitHubAuthTokenDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "github-oauth")
@Path("/login/oauth")
public interface GitHubOAuthRestClient {

  @POST
  @Path("/access_token")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  GitHubAuthTokenDTO getToken(Map<String, String> body);

}
