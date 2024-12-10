package com.example.examTableProject.repository;

import com.example.examTableProject.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByUcId(int ucId);

    @Query("SELECT e.roomId FROM Evaluation e WHERE e.date = :examTime")
    List<Integer> findOccupiedRoomsByTime(@Param("examTime") LocalDateTime examTime);

}
