package com.example.springproject.controller;


import com.example.springproject.config.security.JwtTokenProvider;
import com.example.springproject.dto.BoardDto;
import com.example.springproject.dto.ChangeBoardDto;
import com.example.springproject.service.BoardService;
import io.jsonwebtoken.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;

    public BoardController(BoardService boardService, JwtTokenProvider jwtTokenProvider) {
        this.boardService = boardService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<String> updateBoard(HttpServletRequest request,
                                                @RequestParam Long id,@RequestParam String title,
                                                @RequestParam String content) throws Exception{
        BoardDto boardDto = boardService.getBoardById(id);
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(boardDto.getUserName().equals(userId)) {
            boardService.updateDto(id,title,content);
            return ResponseEntity.status(HttpStatus.OK).body("게시글 수정 완료");
        }
        return ResponseEntity.status(HttpStatus.OK).body("해당 글의 작성자가 아닙니다.");
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardDto> createBoard(@RequestParam String title,@RequestParam String content,@RequestParam String userId,@RequestParam String userName){
        BoardDto boardDto = boardService.createDto(title,content,userId,userName);
        return ResponseEntity.status(HttpStatus.OK).body(boardDto);
    }

    @DeleteMapping()
    @Transactional
    public ResponseEntity<String> deleteBoard(HttpServletRequest request, Long id) throws Exception {
        BoardDto boardDto = boardService.getBoardById(id);
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        if(boardDto.getUserName().equals(userId)) {
            boardService.deleteBoard(id);
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        }
        return ResponseEntity.status(HttpStatus.OK).body("해당 글의 작성자가 아닙니다.");
    }
    //List로 수정
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> listBoard(){
        List<BoardDto> boardDtos = boardService.listBoard();
        return ResponseEntity.status(HttpStatus.OK).body(boardDtos);
    }
    //List로 수정
    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardDto>> listOrderByCreatedAtBoard(){
        List<BoardDto> boardDtos = boardService.listOrderByCreateAt();
        return ResponseEntity.status(HttpStatus.OK).body(boardDtos);
    }
    //List로 수정
    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardDto>> byUserIdBoard(@RequestParam String userId){
        List<BoardDto> boardDtos = boardService.listByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(boardDtos);
    }
    @GetMapping("/")
    public ResponseEntity<List<BoardDto>> BoardById(@RequestParam Long id){
        List<BoardDto> boardDtos = boardService.listOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardDtos);
    }

}
