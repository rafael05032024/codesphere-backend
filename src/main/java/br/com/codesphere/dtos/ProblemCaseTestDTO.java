package br.com.codesphere.dtos;

import jakarta.validation.constraints.NotBlank;

public class ProblemCaseTestDTO {

  @NotBlank(message = "Input não pode ser vazio")
  public String input;

  @NotBlank(message = "ExpectedOutput não pode ser vazio")
  public String expectedOutput;

}
