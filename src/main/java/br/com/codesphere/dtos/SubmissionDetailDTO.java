package br.com.codesphere.dtos;

public class SubmissionDetailDTO {

  public String sourceCode;
  public long id;
  public int status;
  public String observation;
  public String language;
  public SubmissionProblemDTO problem;
  public SubmissionCompilationDetailDTO compilation;

  public SubmissionDetailDTO(String sourceCode, long id, int status, String language, String observation,
      long problemId,
      String problemTitle, double memory, double time, int size) {
    this.id = id;
    this.sourceCode = sourceCode;
    this.status = status;
    this.language = language;
    this.observation = observation;

    SubmissionCompilationDetailDTO compilationInfo = new SubmissionCompilationDetailDTO(memory, time, size);

    this.compilation = compilationInfo;

    SubmissionProblemDTO releatedProblem = new SubmissionProblemDTO(problemId, problemTitle);

    releatedProblem.id = problemId;
    releatedProblem.title = problemTitle;

    this.problem = releatedProblem;
  }

}
