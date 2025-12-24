package br.com.codesphere.repositories;

import br.com.codesphere.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
  
  public UserEntity findByEmail(String email) {
    return find("email", email).firstResult();
  }

}
