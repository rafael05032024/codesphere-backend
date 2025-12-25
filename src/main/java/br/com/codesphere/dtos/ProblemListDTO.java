package br.com.codesphere.dtos;

import java.util.List;

public class ProblemListDTO {

  public List<ProblemListItemDTO> result;

  public ProblemListDTO(List<ProblemListItemDTO> problemList) {
    this.result = problemList;
  }

}
