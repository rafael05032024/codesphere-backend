package br.com.codesphere.exception;

import br.com.codesphere.dtos.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException ex) {

    String message = ex.getConstraintViolations()
        .stream()
        .map(v -> v.getPropertyPath() + " " + v.getMessage())
        .findFirst()
        .orElse("Validation error");

    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity(new ErrorResponseDTO(message))
        .build();
  }
}
