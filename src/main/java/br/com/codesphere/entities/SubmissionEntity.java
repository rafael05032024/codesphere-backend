package br.com.codesphere.entities;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "submission")
public class SubmissionEntity extends PanacheEntity {

  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @Column(name = "status")
  public int status;

  @Column(name = "comment")
  public String comment;

  @Column(name = "source_code", length = 4000)
  public String sourceCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id")
  public ProblemEntity problem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "language_id")
  public LanguageEntity language;

}
