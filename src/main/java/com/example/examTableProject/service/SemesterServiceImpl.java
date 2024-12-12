package com.example.examTableProject.service;

import com.example.examTableProject.model.Semester;
import com.example.examTableProject.repository.SemesterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRespository semesterRespository;

    @Override
    public Semester saveSemester(Semester semester) {
        return semesterRespository.save(semester);
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRespository.findAll();
    }

    @Override
    public Semester getSemesterById(int id) {
        return semesterRespository.findById(id).orElse(null);
    }

    @Override
    public Semester updateSemester(Semester semester) {
        return semesterRespository.save(semester);
    }
}
