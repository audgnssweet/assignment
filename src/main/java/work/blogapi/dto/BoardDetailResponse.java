package work.blogapi.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import work.blogapi.entity.Board;

/*
board관련 응답 기본 dto입니다.
요청과 달리 id, createDate까지 함께 반환하여 많은 정보를 보냈습니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDetailResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


    public BoardDetailResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
    }
}
