package work.blogapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.blogapi.common.Response;
import work.blogapi.exception.exceptions.BoardNotFoundException;
import work.blogapi.exception.exceptions.BoardRequestValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<String> handleBoardNotFoundException(BoardNotFoundException e) {
        return new Response<>(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BoardRequestValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleBoardRequestValidationException(
        BoardRequestValidationException e) {
        return new Response<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}
