package br.com.codesphere.dtos;

public class CategoryListItemDTO {

  public long id;
  public String title;
  public String description;
  public long totalProblems;

  public CategoryListItemDTO(long id, String title, String description, long totalProblems) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.totalProblems = totalProblems;
  }

}
