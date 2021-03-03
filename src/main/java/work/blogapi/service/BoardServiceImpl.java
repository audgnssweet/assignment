package work.blogapi.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.blogapi.dto.BoardRequestDto;
import work.blogapi.dto.BoardResponseDto;
import work.blogapi.entity.Board;
import work.blogapi.exception.exceptions.BoardNotFoundException;
import work.blogapi.repository.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public BoardResponseDto saveBoard(BoardRequestDto boardRequestDto) {
        final Board savedBoard = boardRepository.save(new Board(boardRequestDto));
        return new BoardResponseDto(savedBoard);
    }

    @Transactional
    @Override
    public BoardResponseDto updateBoard(Integer boardId, BoardRequestDto boardRequestDto) {
        final Board foundBoard = boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);

        foundBoard.setTitle(boardRequestDto.getTitle())
            .setContent(boardRequestDto.getContent());
        //영속화로 자동업데이트
        return new BoardResponseDto(foundBoard);
    }

    /*
    delete시 http status no content가 반환 (body없음) 이므로 void형으로 반환합니다.
     */
    @Transactional
    @Override
    public void deleteBoard(Integer boardId) {
        final boolean isExist = boardRepository.existsById(boardId);
        if (!isExist) {
            throw new BoardNotFoundException();
        } else {
            boardRepository.deleteById(boardId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public BoardResponseDto findBoardById(Integer boardId) {
        final Board foundBoard = boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);
        return new BoardResponseDto(foundBoard);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BoardResponseDto> findAllBoards() {
        return boardRepository.findAll().stream()
            .map(BoardResponseDto::new)
            .collect(Collectors.toList());
    }

}
