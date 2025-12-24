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
@Table(name = "problem")
public class ProblemEntity extends PanacheEntity {
  @Column(name = "created_at", updatable = false, insertable = false)
  public LocalDateTime createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  public LocalDateTime updatedAt;

  @Column(name = "template_html")
  public String templateHtml;

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

}
