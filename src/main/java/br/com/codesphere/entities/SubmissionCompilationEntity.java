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
@Table(name = "submission_compilation")
public class SubmissionCompilationEntity extends PanacheEntity {

  @Column(name = "token")
  public String token;

  @Column(name = "stdout")
  public String stdout;

  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "submission_id")
  public SubmissionEntity submission;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_case_test_id")
  public ProblemCaseTestEntity problemCaseTest;

}
