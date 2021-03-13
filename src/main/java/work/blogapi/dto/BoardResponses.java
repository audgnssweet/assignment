package work.blogapi.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import work.blogapi.entity.Board;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponses {

    List<Board> boardList;

    public BoardResponses(final List<Board> boardList) {
        this.boardList = boardList;
    }

}
