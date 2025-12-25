package br.com.codesphere.dtos;

public class ProblemDTO {

  public long id;
  public int timeLimit;
  public String template;
  public String title;
  public String author;

  public ProblemDTO(long id, int timeLimit, String template, String title, String author) {
    this.id = id;
    this.timeLimit = timeLimit;
    this.template = template;
    this.title = title;
    this.author = author;
  }

}
