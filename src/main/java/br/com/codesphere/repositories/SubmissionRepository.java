package br.com.codesphere.repositories;

import br.com.codesphere.entities.SubmissionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubmissionRepository implements PanacheRepository<SubmissionEntity> {

}
