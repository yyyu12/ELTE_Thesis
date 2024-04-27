package hu.elte.inf.backend.common;

import hu.elte.inf.backend.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindException;
import org.apache.http.HttpStatus;
import hu.elte.inf.backend.common.exceptionEnd.*;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Data not found exception
    @ExceptionHandler(value = {ArtistNotFoundException.class, ArtworkNotFoundException.class, CartItemNotFound.class, WishlistItemNotFound.class, UserNotFoundException.class})
    public ResponseEntity<Result> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
                .body(Result.error(HttpStatus.SC_NOT_FOUND, ex.getMessage()));
    }

    // Data already exists exception
    @ExceptionHandler(value = {ArtistExistException.class, ArtworkAlreadyExistsException.class, CartItemAlreadyExists.class, WishlistItemAlreadyExists.class})
    public ResponseEntity<Result> handleConflictException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.SC_CONFLICT)
                .body(Result.error(HttpStatus.SC_CONFLICT, ex.getMessage()));
    }

    // Invalid request parameters
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result> handleBindException(BindException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        String errorMessage = "Invalid request parameters: " + String.join(", ", errors);
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                .body(Result.error(HttpStatus.SC_BAD_REQUEST, errorMessage));
    }

    // Internal server error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleAllUncaughtException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(Result.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + ex.getMessage()));
    }
}

