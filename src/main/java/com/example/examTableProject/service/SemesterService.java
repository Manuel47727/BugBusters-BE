package com.example.examTableProject.service;

import com.example.examTableProject.model.Semester;

import java.util.List;

public interface SemesterService {

    public Semester saveSemester(Semester semester);

    public List<Semester> getAllSemesters();

    public Semester getSemesterById(int id);

    public Semester updateSemester(Semester semester);
}
