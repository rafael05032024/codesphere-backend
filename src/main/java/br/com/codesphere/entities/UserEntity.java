package br.com.codesphere.entities;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends PanacheEntity {
  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @Column(name = "name")
  public String name;

  @Column(name = "email")
  public String email;

}
