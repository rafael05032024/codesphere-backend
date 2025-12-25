package br.com.codesphere.repositories;

import java.util.List;

import br.com.codesphere.entities.SubmissionCompilationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubmissionCompilationRepository implements PanacheRepository<SubmissionCompilationEntity> {

  public List<SubmissionCompilationEntity> listBySubmissionId(Long submissionId) {
    return find("submission.id", submissionId).list();
  }

}
