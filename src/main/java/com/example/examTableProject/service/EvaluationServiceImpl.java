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

    @Override
    public List<Evaluation> getEvaluationsForUC(int ucId) {
        return evaluationRepository.findByUcId(ucId);
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public void deleteEvaluationsForUC(int ucId) {
        List<Evaluation> evaluations = evaluationRepository.findByUcId(ucId);
        if (evaluations != null && !evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations);
        }
    }
}
