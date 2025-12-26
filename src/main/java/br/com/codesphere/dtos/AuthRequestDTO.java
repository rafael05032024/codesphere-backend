package br.com.codesphere.dtos;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO {

  @NotBlank(message = "Email é obrigatório")
  @Email
  @NotNull
  public String email;

}
