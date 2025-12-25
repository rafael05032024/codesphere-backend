package br.com.codesphere.exception;

import org.jboss.logging.Logger;

import br.com.codesphere.dtos.ErrorResponseDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper
    implements ExceptionMapper<Throwable> {

  private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

  @Override
  public Response toResponse(Throwable exception) {

    // Loga stacktrace no servidor
    LOG.error("Erro inesperado", exception);

    // Resposta limpa pro cliente
    return Response
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(new ErrorResponseDTO("Internal Server Error"))
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
