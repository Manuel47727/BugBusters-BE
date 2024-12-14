package com.example.examTableProject.service;

import com.example.examTableProject.model.Evaluation;

import java.util.List;


public interface EvaluationService {

    /**
     * Add a new evaluation to the repository.
     *
     * @param evaluation The evaluation object to be added.
     * @return The saved evaluation object.
     * @throws IllegalArgumentException if any validation constraints are violated.
     */
    public Evaluation addEvaluation(Evaluation evaluation);
    /**
     * Get a list of all evaluations associated with a particular UC.
     *
     * @param id The ID of the UC to retrieve evaluations for.
     * @return A list of evaluations associated with the given UC.
     */
    public List<Evaluation> getEvaluationsForUC(int id);
    /**
     * Get all evaluations across all UCs.
     *
     * @return A list of all evaluations in the repository.
     */
    public List<Evaluation> getAllEvaluations();
    /**
     * Delete all evaluations associated with a particular UC.
     *
     * @param ucId The ID of the UC to delete evaluations for.
     */
    public void deleteEvaluationsForUC(int ucId);
}
