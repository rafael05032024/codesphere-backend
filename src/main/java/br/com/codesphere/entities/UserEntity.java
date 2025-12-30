package br.com.codesphere.entities;

import java.time.LocalDateTime;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

  @Column(name = "login")
  public String login;

  @Column(name = "avatar")
  public String avatar;

  @Column(name = "email")
  public String email;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public List<SubmissionEntity> submissions;

}
