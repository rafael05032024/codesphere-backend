package br.com.codesphere.repositories;

import java.util.List;

import br.com.codesphere.entities.ProblemEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProblemRepository implements PanacheRepository<ProblemEntity> {

  public List<ProblemEntity> findByCategoryId(long categoryId) {
    return find("category.id", categoryId).list();
  }

  public long countByCategoryId(long categoryId) {
    return find("category.id", categoryId).count();
  }

  public List<ProblemEntity> search(String term) {
    if (term == null || term.isBlank()) {
      return find("").list();
    }

    String search = "%" + term.toLowerCase() + "%";

    boolean isNumeric = term.matches("\\d+");

    if (isNumeric) {
      Long id = Long.valueOf(term);

      return find(
          "id = :id " +
              "or lower(description) like :search " +
              "or lower(inputText) like :search " +
              "or lower(outputText) like :search",
          Parameters.with("id", id)
              .and("search", search))
          .list();
    }

    return find(
        "lower(description) like ?1 " +
            "or lower(inputText) like ?1 " +
            "or lower(outputText) like ?1",
        search).list();
  }

}
