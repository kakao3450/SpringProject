package com.example.springproject.service.imple;

import com.example.springproject.dao.BoardDao;
import com.example.springproject.dto.BoardDto;
import com.example.springproject.dto.OrderResponseDto;
import com.example.springproject.dto.ProductResponseDto;
import com.example.springproject.entity.Board;
import com.example.springproject.entity.Order;
import com.example.springproject.entity.Product;
import com.example.springproject.repository.BoardRepository;
import com.example.springproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao, BoardRepository boardRepository) {
        this.boardDao = boardDao;
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> listBoard() {
        List<Board> board = boardDao.listBoard();
        List<BoardDto> boardDtos = board.stream().map(BoardDto::new).collect(Collectors.toList());
        return boardDtos;
    }

    @Override
    public BoardDto createDto(String title, String contents, String userId, String userName) {
        Board board = new Board();
        board.setTitle(title);
        board.setContents(contents);
        board.setUserId(userId);
        board.setUserName(userName);
        board.setCreatedAt(LocalDateTime.now());

        Board createDto = boardDao.insertBoard(board);

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(createDto.getTitle());
        boardDto.setContents(createDto.getContents());
        boardDto.setUserId(createDto.getUserId());
        boardDto.setUserName(createDto.getUserName());
        boardDto.setCreatedAt(createDto.getCreatedAt());
        return boardDto;
    }

    @Override
    public List<BoardDto> listByUserId(String userid) {
        List<Board> board = boardDao.listByUserId(userid);
        List<BoardDto> boardDtos = board.stream().map(BoardDto::new).collect(Collectors.toList());
        return boardDtos;
    }

    @Override
    public BoardDto updateDto(Long id,String title, String content) throws Exception {
        Board changeBoard = boardDao.updateBoard(id,title,content);

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(changeBoard.getTitle());
        boardDto.setContents(changeBoard.getContents());
        boardDto.setUpdatedAt(changeBoard.getUpdatedAt());
        return boardDto;
    }


    @Override
    public List<BoardDto> listOrderByCreateAt() {
        List<Board> boardDtos = boardDao.listOrderByCreateAt();
        List<BoardDto> boardDtoList = boardDtos.stream().map(board -> new BoardDto(board)).collect(Collectors.toList());
        return boardDtoList;
    }

    @Override
    public List<BoardDto> listOrderById(Long id) {
        List<Board> boards = boardDao.listById(id);
        List<BoardDto> boardDtos = boards.stream().map(
                BoardDto::new).collect(Collectors.toList());
        return boardDtos;
    }

    @Override
    public BoardDto getBoardById(Long id) {
        Board board = boardDao.getById(id);

        BoardDto boardDto = new BoardDto();

        boardDto.setTitle(board.getTitle());
        boardDto.setContents(board.getContents());
        boardDto.setUserId(board.getUserId());
        boardDto.setUserName(board.getUserName());
        boardDto.setCreatedAt(board.getCreatedAt());
        boardDto.setUpdatedAt(board.getUpdatedAt());

        return boardDto;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        boardDao.deleteBoard(id);
    }
}
