package br.com.codesphere.dtos;

public class ProblemListItemDTO {

  public String title;

  public long id;

  public boolean solved;

  public boolean attempted;

  public ProblemListItemDTO(String title, long id, boolean solved, boolean attempted) {
    this.title = title;
    this.id = id;
    this.solved = solved;
    this.attempted = attempted;
  }

}
