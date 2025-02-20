package ndgroups.DAShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> AccessDeniedException(AccessDeniedException ex){
        String message = "you don't have permission to access this resource";
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}
