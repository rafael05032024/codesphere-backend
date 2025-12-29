package br.com.codesphere.dtos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProblemDTO {

  @NotBlank(message = "Título é obrigatório")
  @Size(max = 30, message = "Título deve ter no máximo 30 caracteres")
  public String title;

  @NotBlank(message = "Texto de descrição é obrigatório")
  @Size(max = 4000)
  @JsonProperty("description_text")
  public String descriptionText;

  @NotBlank(message = "Texto de entrada é obrigatório")
  @Size(max = 4000)
  @JsonProperty("input_text")
  public String inputText;

  @NotBlank(message = "Texto de saída é obrigatório")
  @Size(max = 4000)
  @JsonProperty("output_text")
  public String outputText;

  @NotNull
  @JsonProperty("time_limit")
  public Integer timeLimit;

  @NotNull
  @JsonProperty("category_id")
  public Long categoryId;

  @NotNull
  @Size(min = 3, message = "Um problema deve ter no mínimo 3 casos teste")
  @JsonProperty("test_cases")
  public ArrayList<ProblemCaseTestDTO> testCases;

}
