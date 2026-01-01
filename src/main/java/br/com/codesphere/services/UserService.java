package br.com.codesphere.services;

import br.com.codesphere.dtos.UserProfileDTO;
import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

  @Inject
  UserRepository userRepository;

  public UserProfileDTO getById(Long userId) {

    UserEntity user = userRepository.findById(userId);
    long totalSolved = user.submissions.stream()
        .filter(s -> s.status == 3)
        .map(s -> s.problem.id)
        .distinct()
        .count();

    return new UserProfileDTO(user.avatar, user.submissions.size(), totalSolved, user.login, user.email,
        user.createdAt);

  }

}
