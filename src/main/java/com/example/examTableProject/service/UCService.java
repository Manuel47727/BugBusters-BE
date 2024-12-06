package com.example.examTableProject.service;

import com.example.examTableProject.model.UC;
import java.util.List;

public interface UCService {

    public UC addUC(UC course);
    public List<UC> getAllUCs();
    public List<UC> getUCsForCurrentSemesterAndCourse(int courseId);
}
