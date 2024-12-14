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

    /**
     * Finds all evaluations associated with a specific unit of curriculum (UC).
     *
     * @param ucId the UC identifier to search for evaluations.
     * @return a list of evaluations associated with the given UC ID.
     */
    List<Evaluation> findByUcId(int ucId);


    /**
     * Finds all room IDs that are occupied at a given time.
     *
     * @param examTime the time at which to find occupied rooms.
     * @return a list of room IDs occupied at the given time.
     */
    @Query("SELECT e.roomId FROM Evaluation e WHERE e.date = :examTime")
    List<Integer> findOccupiedRoomsByTime(@Param("examTime") LocalDateTime examTime);



}
