package br.com.codesphere.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public class ProblemCaseTestDTO {

  @NotBlank(message = "Input não pode ser vazio")
  public String input;

  @NotNull
  @JsonProperty("is_example")
  public Boolean isExample;

  @NotBlank(message = "ExpectedOutput não pode ser vazio")
  @JsonProperty("expected_output")
  public String expectedOutput;

}
