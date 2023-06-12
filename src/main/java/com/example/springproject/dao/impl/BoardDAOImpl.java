package com.example.springproject.dao.impl;

import com.example.springproject.dao.BoardDao;
import com.example.springproject.entity.Board;
import com.example.springproject.entity.Product;
import com.example.springproject.repository.BoardRepository;
import com.example.springproject.repository.QBoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDao {
    private final BoardRepository boardRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QBoardRepository qBoardRepository;

    @Autowired
    public BoardDAOImpl(BoardRepository boardRepository,JPAQueryFactory jpaQueryFactory, QBoardRepository qBoardRepository){
        this.boardRepository = boardRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.qBoardRepository = qBoardRepository;
    }

    @Override
    public Board insertBoard(Board board) {
        Board insertBoard = boardRepository.save(board);
        return insertBoard;
    }

    @Override
    public List<Board> listByUserId(String userId) {
        return boardRepository.findAllByUserId(userId);
    }

    @Override
    public List<Board> listById(Long id) {
        List<Board> listById = boardRepository.findAllById(id);
        return listById;
    }

    @Override
    public List<Board> listBoard() {
        return boardRepository.findAllBy();
    }

    @Override
    public Board updateBoard(Long id,String title, String content) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(id);
        Board updateBoard;
        if(selectBoard.isPresent()){
            Board board = selectBoard.get();
            board.setTitle(title);
            board.setContents(content);
            board.setUpdatedAt(LocalDateTime.now());
            updateBoard= boardRepository.save(board);
        }else{
            throw new Exception();
        }
        return updateBoard;
    }


    @Override
    public List<Board> listOrderByCreateAt() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Board getById(Long id) {
        return boardRepository.getReferenceById(id);
    }

    @Override
    public void deleteBoard(Long number) throws Exception {
        Optional<Board> selectBoard = boardRepository.findById(number);
        if(selectBoard.isPresent()) {
            Board board = selectBoard.get();
            boardRepository.delete(board);
        } else throw new Exception();
    }
}
