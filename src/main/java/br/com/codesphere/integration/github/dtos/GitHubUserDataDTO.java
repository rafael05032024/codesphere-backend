package br.com.codesphere.integration.github.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubUserDataDTO {

  @JsonProperty("login")
  public String login;

  @JsonProperty("name")
  public String name;

  @JsonProperty("avatar_url")
  public String avatarUrl;

}
