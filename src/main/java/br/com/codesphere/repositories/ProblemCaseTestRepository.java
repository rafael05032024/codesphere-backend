package br.com.codesphere.repositories;

import br.com.codesphere.entities.ProblemCaseTestEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProblemCaseTestRepository implements PanacheRepository<ProblemCaseTestEntity> {

}
