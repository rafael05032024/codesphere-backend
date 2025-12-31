package br.com.codesphere.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfileDTO {

  @JsonProperty("avatar_url")
  public String avatarUrl;

  @JsonProperty("total_submissions")
  public long totalSubmissions;

  @JsonProperty("total_resolved")
  public long totalResolved;

  @JsonProperty("login")
  public String login;

  @JsonProperty("email")
  public String email;

  @JsonProperty("created_at")
  public LocalDateTime createdAt;

  public UserProfileDTO(String avatarUrl, long totalSubmissions, long totalResolved, String login,
      String email, LocalDateTime createdAt) {
    this.avatarUrl = avatarUrl;
    this.totalSubmissions = totalSubmissions;
    this.totalResolved = totalResolved;
    this.login = login;
    this.email = email;
    this.createdAt = createdAt;
  }

}
