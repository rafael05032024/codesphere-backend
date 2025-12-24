package br.com.codesphere.integration.judge0.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Judge0SubmissionRequestDTO {
  
  @JsonProperty("language_id")
  public int languageId;

  @JsonProperty("source_code")
  public String sourceCode;
 
  @JsonProperty("stdin")
  public String stdIn;

  public Judge0SubmissionRequestDTO(int languageId, String sourceCode, String stdIn) {
    this.languageId = languageId;
    this.sourceCode = sourceCode;
    this.stdIn = stdIn;
  }

}
