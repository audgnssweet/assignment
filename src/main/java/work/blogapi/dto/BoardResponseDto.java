package work.blogapi.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import work.blogapi.entity.Board;

/*
board관련 응답 기본 dto입니다.
요청과 달리 id, createDate까지 함께 반환하여 많은 정보를 보냈습니다.
 */
@Setter
@Getter
@Accessors(chain = true)
public class BoardResponseDto extends BoardDto {

    private Integer id;
    private LocalDateTime createDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = board.getCreateDate();
    }
}
