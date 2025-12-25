package br.com.codesphere.repositories;

import java.util.List;

import br.com.codesphere.entities.SubmissionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubmissionRepository implements PanacheRepository<SubmissionEntity> {

  public List<SubmissionEntity> listByUserId(Long userId) {
    return find("user.id", userId).list();
  }

  public List<SubmissionEntity> listByStatus(int status) {
    return find("status", status).list();
  }

}
