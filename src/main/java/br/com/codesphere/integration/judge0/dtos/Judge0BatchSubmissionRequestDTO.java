package br.com.codesphere.integration.judge0.dtos;

import java.util.List;

public class Judge0BatchSubmissionRequestDTO {
  
  public List<Judge0SubmissionRequestDTO> submissions;

  public Judge0BatchSubmissionRequestDTO(List<Judge0SubmissionRequestDTO> submissions) {
    this.submissions = submissions;
  }

}
