package foo.guilherme.webnovel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<?> handleResponseException(ResponseException exc) {
        var details = new ExceptionDetails();
        details.setStatus(exc.getStatus());
        details.setMessage(exc.getMessage());
        return new ResponseEntity<>(details, details.getStatus());
    }
}
