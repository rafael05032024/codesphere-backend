package br.com.codesphere.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class LanguageEntity extends PanacheEntityBase {

  @Id
  @Column(name = "id")
  public Integer id;

  @Column(name = "name")
  public String name;

}
