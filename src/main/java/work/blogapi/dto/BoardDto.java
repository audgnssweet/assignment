package work.blogapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/*
Board 관련 요청시 반드시 포함되어야하는 부분을 추상클래스로 정의하고,
필요한 부분은 상속받아서 만들도록 하였습니다.
코드의 집중이 목적이었습니다.
 */
@Getter
@Setter
public abstract class BoardDto {

    @NotEmpty(message = "title을 입력해야 합니다")
    @Size(max = 50, message = "제목은 50자를 초과할 수 없습니다")
    protected String title;

    @NotEmpty(message = "content를 입력해야 합니다")
    protected String content;

}
