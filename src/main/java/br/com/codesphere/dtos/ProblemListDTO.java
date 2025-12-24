package br.com.codesphere.dtos;

import java.util.List;

public class ProblemListDTO {

  public List<ProblemDTO> result;

  public ProblemListDTO(List<ProblemDTO> problemList) {
    this.result = problemList;
  }

}
