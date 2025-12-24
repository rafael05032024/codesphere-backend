package br.com.codesphere.dtos;

public class ProblemDTO {
  
  public long id;
  public String template;
  public String title;

  public ProblemDTO(long id, String template, String title) {
    this.id = id;
    this.template = template;
    this.title = title;
  }

}
