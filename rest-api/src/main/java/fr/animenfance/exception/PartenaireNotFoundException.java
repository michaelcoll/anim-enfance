package fr.animenfance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Partenaire")  // 404
public class PartenaireNotFoundException extends Exception {
  public PartenaireNotFoundException() {
  }

  public PartenaireNotFoundException(String message) {
    super(message);
  }

  public PartenaireNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public PartenaireNotFoundException(Throwable cause) {
    super(cause);
  }

  public PartenaireNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
