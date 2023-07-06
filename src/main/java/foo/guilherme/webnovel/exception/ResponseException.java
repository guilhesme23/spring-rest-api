package foo.guilherme.webnovel.exception;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {
    private HttpStatus status;
    public ResponseException(HttpStatus statusCode, String message) {
        super(message);
        this.status = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
