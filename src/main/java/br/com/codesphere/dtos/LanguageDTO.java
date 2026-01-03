package br.com.codesphere.dtos;

public class LanguageDTO {

  public String name;

  public String template;

  public long id;

  public LanguageDTO(long id, String name, String template) {
    this.id = id;
    this.name = name;
    this.template = template;
  }

}
