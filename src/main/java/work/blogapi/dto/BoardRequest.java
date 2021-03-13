package work.blogapi.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.blogapi.entity.Board;

/*
board관련 요청 기본 dto입니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BoardRequest {

    @Valid
    @Size(max = 50)
    @NotEmpty
    private String title;

    @Valid
    @NotEmpty
    private String content;

    public Board toEntity() {
        return Board.builder()
            .title(title)
            .content(content)
            .build();
    }
}
