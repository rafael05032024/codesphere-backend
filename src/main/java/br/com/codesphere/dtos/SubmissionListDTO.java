package br.com.codesphere.dtos;

import java.util.List;

public class SubmissionListDTO {

  public List<SubmissionListItemDTO> result;

  public SubmissionListDTO(List<SubmissionListItemDTO> list) {
    this.result = list;
  }

}
