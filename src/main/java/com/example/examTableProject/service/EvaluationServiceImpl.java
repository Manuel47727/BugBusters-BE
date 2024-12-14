package com.example.examTableProject.service;

import com.example.examTableProject.model.Evaluation;
import com.example.examTableProject.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    /**
     * Save an evaluation to the database.
     *
     * @param evaluation The evaluation to add.
     * @return The saved evaluation.
     * @throws IllegalArgumentException if any of the evaluation fields are invalid.
     */
    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        if (evaluation.getType() == null || evaluation.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Evaluation type cannot be empty");
        }
        if (evaluation.getWeight() <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        if (evaluation.getDate() == null) {
            throw new IllegalArgumentException("Evaluation date cannot be null");
        }
        if (evaluation.getRoomId() <= 0) {
            throw new IllegalArgumentException("Room ID must be a positive number");
        }
        if (evaluation.getStudentNum() <= 0) {
            throw new IllegalArgumentException("Number of students must be greater than 0");
        }

        return evaluationRepository.save(evaluation);
    }

    /**
     * Retrieve a list of evaluations associated with the specified unit of curriculum (UC).
     *
     * @param ucId The ID of the UC for which to retrieve evaluations.
     * @return A list of evaluations related to the given UC ID.
     */
    @Override
    public List<Evaluation> getEvaluationsForUC(int ucId) {
        return evaluationRepository.findByUcId(ucId);
    }

    /**
     * Retrieves a list of all evaluations in the database.
     *
     * @return A list of all evaluations.
     */
    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    /**
     * Delete all evaluations associated with a particular unit of curriculum (UC).
     *
     * @param ucId The ID of the UC for which to delete evaluations.
     */
    @Override
    public void deleteEvaluationsForUC(int ucId) {
        List<Evaluation> evaluations = evaluationRepository.findByUcId(ucId);
        if (evaluations != null && !evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations);
        }
    }
}
