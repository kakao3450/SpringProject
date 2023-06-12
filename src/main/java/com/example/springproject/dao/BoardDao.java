package com.example.springproject.dao;

import com.example.springproject.entity.Board;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface BoardDao {

    Board insertBoard(Board board);
    List<Board> listByUserId(String userId);

    List<Board> listById(Long id);

    List<Board> listBoard();
    Board updateBoard(Long id,String title, String content) throws Exception;
    List<Board> listOrderByCreateAt();

    Board getById(Long id);
    void deleteBoard(Long number) throws Exception;

}
