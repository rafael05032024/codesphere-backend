package br.com.codesphere.dtos;

import java.util.ArrayList;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProblemDTO {

  @NotBlank(message = "Título é obrigatório")
  @Size(max = 30, message = "Título deve ter no máximo 30 caracteres")
  public String title;

  @NotBlank(message = "Template é obrigatório")
  @Size(max = 4000, message = "Template deve ter no máximo 4000 caracteres")
  public String templateHtml;

  @NotNull
  public Integer timeLimit;

  @NotNull
  public Long categoryId;

  @NotNull
  @Size(min = 3, message = "Um problema deve ter no mínimo 3 casos teste")
  public ArrayList<ProblemCaseTestDTO> testCases;

}
