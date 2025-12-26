package br.com.codesphere.dtos;

import java.util.List;

public class ProblemListDTO {

  public List<ProblemListItemDTO> result;

  public long total;

  public ProblemListDTO(List<ProblemListItemDTO> problemList, long total) {
    this.result = problemList;
    this.total = total;
  }

}
