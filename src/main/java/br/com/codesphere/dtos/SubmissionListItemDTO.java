package br.com.codesphere.dtos;

import java.time.LocalDateTime;

public class SubmissionListItemDTO {

  public long id;
  public SubmissionProblemDTO problem;
  public int status;
  public String language;
  public LocalDateTime createdAt;

  public SubmissionListItemDTO(long id, String problemTitle, long problemId, int status, String languageName,
      LocalDateTime createdAt) {
    this.id = id;
    this.status = status;
    this.language = languageName;
    this.createdAt = createdAt;

    SubmissionProblemDTO releatedProblem = new SubmissionProblemDTO(problemId, problemTitle);

    releatedProblem.id = problemId;
    releatedProblem.title = problemTitle;

    this.problem = releatedProblem;
  }

}
