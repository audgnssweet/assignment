package work.blogapi.exception.exceptions;

/*
Board관련 요청시 잘못된 요청을 할 경우 (파라미터 validation)
 */
public class BoardRequestValidationException extends RuntimeException {

    public BoardRequestValidationException(String message) {
        super(message);
    }
}
