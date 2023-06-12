package com.example.springproject.service;

import com.example.springproject.dao.BoardDao;
import com.example.springproject.dto.BoardDto;
import com.example.springproject.dto.ProductResponseDto;
import com.example.springproject.entity.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface BoardService {
    List<BoardDto> listBoard();
    BoardDto createDto (String title, String contents, String userId, String userName);
    List<BoardDto> listByUserId(String userid);
    BoardDto updateDto(Long id,String title, String contents) throws Exception;

    List<BoardDto> listOrderByCreateAt();

    List<BoardDto> listOrderById(Long id);

    BoardDto getBoardById(Long id);
    void deleteBoard(Long id) throws Exception;
}
