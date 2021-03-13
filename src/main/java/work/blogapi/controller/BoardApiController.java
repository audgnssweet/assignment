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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.blogapi.common.Response;
import work.blogapi.dto.BoardRequest;
import work.blogapi.dto.BoardDetailResponse;
import work.blogapi.dto.BoardResponses;
import work.blogapi.entity.Board;
import work.blogapi.service.BoardService;

/*
API는 Restful하게 설계하였습니다.
 */
@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<BoardResponses> getALlBoards() {
        final List<Board> all = boardService.findAllBoards();
        return new Response<>(HttpStatus.OK, new BoardResponses(all));
    }

    @GetMapping("/{boardId}")
    @ResponseStatus(HttpStatus.OK)
    public Response<BoardDetailResponse> getBoardDetail(@PathVariable Long boardId) {
        final Board found = boardService.findBoardById(boardId);
        return new Response<>(HttpStatus.OK, new BoardDetailResponse(found));
    }

    /*
    post, put은 validation check가 들어갔습니다.
    post, put은 httpstatus.created와 더불어
    header에 자원 위치를 함께 응답했습니다.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<BoardDetailResponse> postBoard(
        @Valid @RequestBody BoardRequest boardRequestDto,
        HttpServletResponse response,
        BindingResult bindingResult
    ) {
        final Board saved = boardService.saveBoard(boardRequestDto);
        response.setHeader("Content-Location", "/board/" + saved.getId());
        return new Response<>(HttpStatus.CREATED, new BoardDetailResponse(saved));
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<BoardDetailResponse> updateBoard(
        @PathVariable Long boardId,
        @Valid @RequestBody BoardRequest dto,
        HttpServletResponse response,
        BindingResult bindingResult
    ) {
        final Board saved = boardService.updateBoard(boardId, dto);
        response.setHeader("Content-Location", "/board/" + saved.getId());
        return new Response<>(HttpStatus.CREATED, new BoardDetailResponse(saved));
    }

    @DeleteMapping("/{boardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoardById(boardId);
    }
}
