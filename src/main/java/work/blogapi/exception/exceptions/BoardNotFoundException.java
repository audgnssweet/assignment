package work.blogapi.exception.exceptions;

/*
없는 Board를 찾거나 요청할 경우
 */
public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super("해당 ID의 Board는 존재하지 않습니다");
    }

}
