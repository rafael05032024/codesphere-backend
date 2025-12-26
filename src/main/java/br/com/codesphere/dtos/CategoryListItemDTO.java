package br.com.codesphere.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryListItemDTO {

  public long id;

  public String title;

  public String description;

  @JsonProperty("total_problems")
  public long totalProblems;

  public CategoryListItemDTO(long id, String title, String description, long totalProblems) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.totalProblems = totalProblems;
  }

}
