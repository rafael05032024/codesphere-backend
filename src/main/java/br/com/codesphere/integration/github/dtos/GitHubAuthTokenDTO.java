package br.com.codesphere.integration.github.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubAuthTokenDTO {

  @JsonProperty("access_token")
  public String accessToken;

  @JsonProperty("tokenType")
  public String token_type;

  @JsonProperty("scope")
  public String scope;

}
