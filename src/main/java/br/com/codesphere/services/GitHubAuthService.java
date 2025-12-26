package br.com.codesphere.services;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.integration.github.GitHubOAuthRestClient;
import br.com.codesphere.integration.github.GitHubRestClient;
import br.com.codesphere.integration.github.dtos.GitHubAuthTokenDTO;
import br.com.codesphere.integration.github.dtos.GitHubUserDataDTO;
import br.com.codesphere.integration.github.dtos.GitHubUserEmailDTO;
import br.com.codesphere.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GitHubAuthService {

  @ConfigProperty(name = "github.client-id")
  String clientId;

  @ConfigProperty(name = "github.client-secret")
  String clientSecret;

  @ConfigProperty(name = "github.redirect-uri")
  String redirectUri;

  @Inject
  UserRepository userRepository;

  @Inject
  JWTService jwtService;

  @RestClient
  @Inject
  GitHubOAuthRestClient githuOAuthRestClient;

  @RestClient
  @Inject
  GitHubRestClient gitHubRestClient;

  @Transactional
  public String auth(String code) {
    Map<String, String> body = Map.of(
        "client_id", clientId,
        "client_secret", clientSecret,
        "code", code);

    GitHubAuthTokenDTO token = githuOAuthRestClient.getToken(body);

    String authorization = "Bearer " + token.accessToken;

    System.out.println(authorization);

    List<GitHubUserEmailDTO> userEmails = gitHubRestClient.getUserEmails(authorization);

    String primaryEmail = userEmails.stream().filter((email) -> email.primary == true).findFirst().get().email;

    UserEntity user = userRepository.findByEmail(primaryEmail);

    if (Objects.isNull(user)) {
      GitHubUserDataDTO userData = gitHubRestClient.getUserData(authorization);

      UserEntity newUser = new UserEntity();

      newUser.login = userData.login;
      newUser.avatar = userData.avatarUrl;
      newUser.name = userData.name;
      newUser.email = primaryEmail;

      newUser.persist();

      return jwtService.sign(newUser.id, newUser.email);
    }

    return jwtService.sign(user.id, user.email);

  }

  public String buildLoginUrl(String state) {
    return "https://github.com/login/oauth/authorize" +
        "?client_id=" + clientId +
        "&redirect_uri=" + redirectUri +
        "&scope=user:email" +
        "&state=" + state;

  }

}
