package br.com.codesphere.services;

import java.util.Objects;

import br.com.codesphere.dtos.AuthRequestDTO;
import br.com.codesphere.dtos.AuthResponseDTO;
import br.com.codesphere.entities.UserEntity;
import br.com.codesphere.exception.ApplicationException;
import br.com.codesphere.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {

  @Inject
  UserRepository userRepository;

  @Inject
  JWTService jwtService;

  public AuthResponseDTO signIn(AuthRequestDTO request) throws ApplicationException {
    UserEntity user = userRepository.findByEmail(request.email);

    if (Objects.isNull(user)) {
      throw new ApplicationException("Usuário não encontrado!", 403);
    }

    return new AuthResponseDTO(jwtService.sign(user.id, user.email));
  }

}
