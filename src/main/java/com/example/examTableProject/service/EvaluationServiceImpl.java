package com.example.examTableProject.service;

import com.example.examTableProject.model.Course;
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
}
