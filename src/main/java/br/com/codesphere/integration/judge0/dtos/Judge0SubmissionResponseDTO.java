package br.com.codesphere.integration.judge0.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Judge0SubmissionResponseDTO {
  
  @JsonProperty("source_code")
  public String sourceCode;

  @JsonProperty("language_id")
  public int languageId;

  @JsonProperty("stdin")
  public String stdIn;

  @JsonProperty("stdout")
  public String stdOut;

  @JsonProperty("status_id")
  public int statusId;

  @JsonProperty("created_at")
  public String createdAt;

  @JsonProperty("fineshed_at")
  public String fineshedAt;

}
