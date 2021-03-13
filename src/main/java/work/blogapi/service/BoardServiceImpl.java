package work.blogapi.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.blogapi.dto.BoardRequest;
import work.blogapi.entity.Board;
import work.blogapi.exception.exceptions.BoardNotFoundException;
import work.blogapi.repository.BoardRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;


    @Override
    public Board saveBoard(BoardRequest dto) {
        return boardRepository.save(dto.toEntity());
    }

    @Override
    public Board updateBoard(Long boardId, BoardRequest dto) {
        final Board foundBoard = boardRepository.findById(boardId)
            .orElseThrow(BoardNotFoundException::new);

        return foundBoard.updateBoard(dto.getTitle(), dto.getContent());
    }

    @Override
    public void deleteBoardById(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new BoardNotFoundException();
        }
        boardRepository.deleteById(id);
    }

    @Override
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
    }

    @Override
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }
}
