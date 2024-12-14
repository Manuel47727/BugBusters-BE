package com.example.examTableProject.service;

import com.example.examTableProject.model.UC;
import java.util.List;

public interface UCService {

    /**
     * Adds a new unit of curriculum (UC) to the repository.
     *
     * @param course The UC to be added.
     * @return The added UC.
     */
    public UC addUC(UC course);
    /**
     * Returns a list of all UCs stored in the database.
     *
     * @return A list of all UCs.
     */
    public List<UC> getAllUCs();
    /**
     * Get UCs for the current semester and course.
     *
     * @param courseId The ID of the course.
     * @return A list of UCs for the current semester and course.
     */
    public List<UC> getUCsForCurrentSemesterAndCourse(int courseId);
    /**
     * Checks if the total weight of all evaluations for a given UC is 100%.
     *
     * @param ucId The ID of the UC to validate evaluation weights for.
     * @return True if the total weight of all evaluations is 100%, false otherwise.
     */
    public boolean validateEvaluationWeights(int ucId);
    /**
     * Closes a unit of curriculum (UC) after ensuring that the total weight
     * of associated evaluations is 100%.
     *
     * @param ucId The ID of the UC to be closed.
     * @throws RuntimeException if the UC is not found or if the total weight
     *                          of evaluations is not 100%.
     */
    public void closeUC(int ucId);
    /**
     * Opens a previously closed unit of curriculum (UC).
     *
     * @param ucId The ID of the UC to be opened.
     * @throws RuntimeException if the UC is not found.
     */
    public void openUC(int ucId);
    /**
     * Checks if a given unit of curriculum (UC) is closed.
     *
     * @param ucId The ID of the UC to check.
     * @return True if the UC is closed, false otherwise.
     */
    public boolean isUCClosed(int ucId);
    /**
     * Gets a unit of curriculum (UC) by its ID.
     *
     * @param ucId The ID of the UC to be retrieved.
     * @return The UC with the given ID, or null if no such UC exists.
     */
    public UC getUC(int ucId);
    /**
     * Updates a unit of curriculum (UC) with the given values.
     *
     * @param uc The UC to be updated.
     * @return The updated UC.
     */
    public UC updateUC(UC uc);
    /**
     * Deletes all evaluations associated with a given unit of curriculum (UC).
     *
     * @param ucId The ID of the UC whose evaluations are to be deleted.
     */
    void deleteEvaluationsByUCId(int ucId);
    /**
     * Deletes a unit of curriculum (UC) and all its evaluations.
     *
     * @param ucId The ID of the UC to be deleted.
     */
    void deleteUC(int ucId);
}
