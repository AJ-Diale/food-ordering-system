package foodorderingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class CategoryNotFoundException extends RuntimeException {
    /*Adding Exception Handling*/

    public CategoryNotFoundException(String message) {
        super(message);
    }


}