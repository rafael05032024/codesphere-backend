package br.com.codesphere.exception;

import br.com.codesphere.dtos.ErrorResponseDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

  @Override
  public Response toResponse(ApplicationException ex) {
    return Response
        .status(ex.httpCode)
        .entity(new ErrorResponseDTO(ex.getMessage()))
        .build();
  }

}
