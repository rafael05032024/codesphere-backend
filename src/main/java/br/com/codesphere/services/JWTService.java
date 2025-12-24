package br.com.codesphere.services;

import java.time.Duration;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JWTService {
  
  public String sign(Long userId, String email) {
     return Jwt.issuer("api-backend")
                .subject(email)
                .claim("userId", userId)
                .expiresIn(Duration.ofHours(2))
                .sign();
  }

}
