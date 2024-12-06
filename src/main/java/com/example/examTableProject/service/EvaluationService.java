package com.example.examTableProject.service;

import com.example.examTableProject.model.Evaluation;

import java.util.List;


public interface EvaluationService {

    public Evaluation addEvaluation(Evaluation evaluation);
    public List<Evaluation> getEvaluationsForUC(int id);
    public List<Evaluation> getAllEvaluations();
}
