package br.com.codesphere.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemDetailDTO {

  public long id;

  @JsonProperty("time_limit")
  public int timeLimit;

  @JsonProperty("description_text")
  public String descriptionText;

  @JsonProperty("input_text")
  public String inputText;

  @JsonProperty("output_text")
  public String outputText;

  public String title;

  @JsonProperty("author_name")
  public String authorName;

  @JsonProperty("category")
  public ProblemListItemCategoryDTO category;

  @JsonProperty("example_test_cases")
  public List<ProblemListItemTestCaseDTO> exampleTestCases;

  public ProblemDetailDTO(long id, int timeLimit, String descriptionText, String inputText, String outputText,
      String title, String author, String categoryName, long CategoryId,
      List<ProblemListItemTestCaseDTO> exmpleTestCases) {
    this.id = id;
    this.timeLimit = timeLimit;
    this.descriptionText = descriptionText;
    this.inputText = inputText;
    this.outputText = outputText;
    this.title = title;
    this.authorName = author;
    this.category = new ProblemListItemCategoryDTO(categoryName, CategoryId);
    this.exampleTestCases = exmpleTestCases;
  }
}
