package br.com.codesphere.dtos;

public class SubmissionDTO {
  
  public String sourceCode;
  public long id;
  public int status;

  public SubmissionDTO(String sourceCode, long id, int status) {
    this.id = id;
    this.sourceCode = sourceCode;
    this.status = status;
  }

}
