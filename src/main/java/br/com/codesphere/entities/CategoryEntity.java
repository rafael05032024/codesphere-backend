package br.com.codesphere.entities;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity extends PanacheEntity {
    @Column(name = "created_at", updatable = false, insertable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    public LocalDateTime updatedAt;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

}
