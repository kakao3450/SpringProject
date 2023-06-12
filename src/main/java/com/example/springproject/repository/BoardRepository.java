package com.example.springproject.repository;

import com.example.springproject.dto.BoardDto;
import com.example.springproject.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List findAllByOrderByCreatedAtDesc();

    List<Board> findAllById(Long id);

    List<Board> findAllByUserId(String userId);

    List<Board> findAllBy();
}
