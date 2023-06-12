package com.example.springproject.repository;

import com.example.springproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QBoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {
}
