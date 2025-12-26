package br.com.codesphere.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public class CreateSubmissionDTO {

  @NotBlank
  @JsonProperty("source_code")
  public String sourceCode;

  @NotNull
  @JsonProperty("problem_id")
  public Long problemId;

  @NotNull
  @JsonProperty("language_id")
  public Long languageId;

}
