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
@Table(name = "problem_case_test")
public class ProblemCaseTestEntity extends PanacheEntity {
  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @Column(name = "input")
  public String input;

  @Column(name = "is_example")
  public Boolean isExample;

  @Column(name = "expected_output")
  public String expectedOutput;

  @Column(name = "time_limit")
  public int timeLimit;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id")
  public ProblemEntity problem;

}
