package pl.maks.carrental.config.exceptionHandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.maks.carrental.exception.CarRentalNotFoundException;
import pl.maks.carrental.exception.CarRentalValidationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CarRentalExceptionHandler {

    @ExceptionHandler({CarRentalNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(CarRentalNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CarRentalValidationException.class)
    public ResponseEntity<String> handleValidationException(CarRentalValidationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.toString());
    }
}
