package br.com.codesphere.entities;

import java.time.LocalDateTime;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "problem")
public class ProblemEntity extends PanacheEntity {
  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @Column(name = "description", length = 4000)
  public String description;

  @Column(name = "input_text", length = 4000)
  public String inputText;

  @Column(name = "output_text", length = 4000)
  public String outputText;

  @Column(name = "title")
  public String title;

  @Column(name = "time_limit")
  public int timeLimit;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author")
  public UserEntity author;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  public CategoryEntity category;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id")
  public List<SubmissionEntity> submissions;

}
