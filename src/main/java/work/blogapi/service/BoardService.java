package work.blogapi.service;

import java.util.List;
import work.blogapi.dto.BoardRequestDto;
import work.blogapi.dto.BoardResponseDto;

/*
일대일 매칭이라 꼭 Interface로 지정해야만 하는 이유가 있는 것은 아니었지만,
Interface가 해당 기능의 명세서와같은 역할을 한다 생각하여 정의했습니다.
 */
public interface BoardService {

    BoardResponseDto saveBoard(BoardRequestDto boardDto);

    BoardResponseDto updateBoard(Integer boardId, BoardRequestDto boardDto);

    void deleteBoard(Integer id);

    BoardResponseDto findBoardById(Integer id);

    List<BoardResponseDto> findAllBoards();
}
