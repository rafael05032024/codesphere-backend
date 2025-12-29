package br.com.codesphere.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemListItemTestCaseDTO {

  @JsonProperty("input")
  public String input;

  @JsonProperty("expected_output")
  public String expectedOutput;

  public ProblemListItemTestCaseDTO(String input, String expectedOutput) {
    this.input = input;
    this.expectedOutput = expectedOutput;
  }

}
