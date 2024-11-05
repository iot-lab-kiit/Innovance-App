package in.iot.lab.innovance.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception) {
        return ResponseEntity
                .internalServerError()
                .build();
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
