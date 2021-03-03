package work.blogapi.common;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
표준 응답을 위한 dto. 제네릭 타입으로 선언.
 */
@Getter
public class Response<T> {

    private final Integer status;
    private final T data;
    private final LocalDateTime time;

    public Response(HttpStatus httpStatus, T data) {
        this.status = httpStatus.value();
        this.data = data;
        this.time = LocalDateTime.now();
    }

}
