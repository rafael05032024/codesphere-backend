package br.com.codesphere.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_control")
public class JobControlEntity extends PanacheEntityBase {

  @Id
  @Column(name = "name")
  public String name;

  @Column(name = "is_running")
  public boolean isRunning;

}
