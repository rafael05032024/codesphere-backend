package br.com.codesphere.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemDetailDTO {

  public long id;

  @JsonProperty("time_limit")
  public int timeLimit;

  public String template;

  public String title;

  @JsonProperty("author_name")
  public String authorName;

  @JsonProperty("category_id")
  public long categoryId;

  public ProblemDetailDTO(long id, int timeLimit, String template, String title, String author, long categoryId) {
    this.id = id;
    this.timeLimit = timeLimit;
    this.template = template;
    this.title = title;
    this.authorName = author;
    this.categoryId = categoryId;
  }

}
