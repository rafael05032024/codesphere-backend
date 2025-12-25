package br.com.codesphere.repositories;

import br.com.codesphere.entities.JobControlEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobControlRepository implements PanacheRepository<JobControlEntity> {

  public JobControlEntity findByName(String name) {
    return find("name", name).firstResult();
  }

}
