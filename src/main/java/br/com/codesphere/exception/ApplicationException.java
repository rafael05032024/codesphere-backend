package br.com.codesphere.exception;

public class ApplicationException extends RuntimeException {
  public int httpCode;

  public ApplicationException(String message, int httpCode) {
    super(message);

    this.httpCode = httpCode;
  }

}
