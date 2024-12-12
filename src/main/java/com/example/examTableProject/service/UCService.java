package com.example.examTableProject.service;

import com.example.examTableProject.model.UC;
import java.util.List;

public interface UCService {

    public UC addUC(UC course);
    public List<UC> getAllUCs();
    public List<UC> getUCsForCurrentSemesterAndCourse(int courseId);
    public boolean validateEvaluationWeights(int ucId);
    public void closeUC(int ucId);
    public void openUC(int ucId);
    public boolean isUCClosed(int ucId);
    public UC getUC(int ucId);
    public UC updateUC(UC uc);
    void deleteEvaluationsByUCId(int ucId);
    void deleteUC(int ucId);
}
