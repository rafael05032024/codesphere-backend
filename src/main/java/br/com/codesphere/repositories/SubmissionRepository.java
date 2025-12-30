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

  public SubmissionEntity findByIdAndUserId(Long userId, Long id) {
    return find("user.id = ?1 AND id = ?2", userId, id).firstResult();
  };

  public List<SubmissionEntity> listByStatus(int status) {
    return find("status", status).list();
  }

  public List<Integer> listStatusByProblemId(long problemId) {
    return getEntityManager()
        .createQuery("""
                select s.status
                from SubmissionEntity s
                where s.problem.id = :problemId
            """, Integer.class)
        .setParameter("problemId", problemId)
        .getResultList();
  }

}
