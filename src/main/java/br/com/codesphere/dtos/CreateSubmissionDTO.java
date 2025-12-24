package br.com.codesphere.dtos;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public class CreateSubmissionDTO {
  
  @NotBlank
  public String sourceCode;

  @NotNull
  public Long problemId;

}
