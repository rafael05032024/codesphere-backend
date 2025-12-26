package br.com.codesphere.integration.github;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.codesphere.integration.github.dtos.GitHubUserDataDTO;
import br.com.codesphere.integration.github.dtos.GitHubUserEmailDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "github-api")
public interface GitHubRestClient {

  @GET
  @Path("/user")
  GitHubUserDataDTO getUserData(@HeaderParam("Authorization") String token);

  @GET
  @Path("/user/emails")
  List<GitHubUserEmailDTO> getUserEmails(@HeaderParam("Authorization") String token);

}
