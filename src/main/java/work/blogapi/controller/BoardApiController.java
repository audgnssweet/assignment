package work.blogapi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.blogapi.common.Response;
import work.blogapi.dto.BoardRequestDto;
import work.blogapi.dto.BoardResponseDto;
import work.blogapi.service.BoardService;

/*
API는 Restful하게 설계하였습니다.
 */
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    public Response<List<BoardResponseDto>> getALlBoards() {
        return new Response<>(HttpStatus.OK, boardService.findAllBoards());
    }

    @GetMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<BoardResponseDto> getBoardDetail(@PathVariable Integer boardId) {
        return new Response<>(HttpStatus.OK, boardService.findBoardById(boardId));
    }

    /*
    post, put은 validation check가 들어갔습니다.
    post, put은 httpstatus.created와 더불어
    header에 자원 위치를 함께 응답했습니다.
     */
    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<BoardResponseDto> postBoard(
        @Valid @RequestBody BoardRequestDto boardRequestDto,
        HttpServletResponse response,
        BindingResult bindingResult
    ) {
        final BoardResponseDto savedBoard = boardService.saveBoard(boardRequestDto);
        response.setHeader("Content-Location", "/board/" + savedBoard.getId());
        return new Response<>(HttpStatus.CREATED, savedBoard);
    }

    @PutMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<BoardResponseDto> updateBoard(
        @PathVariable Integer boardId,
        @Valid @RequestBody BoardRequestDto boardRequestDto,
        HttpServletResponse response,
        BindingResult bindingResult
    ) {
        final BoardResponseDto savedBoard = boardService.updateBoard(boardId, boardRequestDto);
        response.setHeader("Content-Location", "/board/" + savedBoard.getId());
        return new Response<>(HttpStatus.CREATED, savedBoard);
    }

    @DeleteMapping("/board/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable Integer boardId) {
        boardService.deleteBoard(boardId);
    }
}
