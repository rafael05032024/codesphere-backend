package br.com.codesphere.repositories;

import java.util.List;

import br.com.codesphere.entities.ProblemEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProblemRepository implements PanacheRepository<ProblemEntity> {

  public List<ProblemEntity> findByCategoryId(long categoryId) {
    return find("category.id", categoryId).list();
  }

  public long countByCategoryId(long categoryId) {
    return find("category.id", categoryId).count();
  }

}
