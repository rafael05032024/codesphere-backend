package br.com.codesphere.dtos;

public class SubmissionCompilationDetailDTO {

  public double memory;
  public double time;
  public int size;

  public SubmissionCompilationDetailDTO(double memory, double time, int size) {
    this.memory = memory;
    this.time = time;
    this.size = size;
  }

}
