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
        if (semester.getNumSemester() <= 0) {
            throw new IllegalArgumentException("Semester number must be greater than 0");
        }
        if (semester.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (semester.getEndDate() == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (semester.getStartDate().isAfter(semester.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

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
        if (semester.getNumSemester() <= 0) {
            throw new IllegalArgumentException("Semester number must be greater than 0");
        }
        if (semester.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (semester.getEndDate() == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (semester.getStartDate().isAfter(semester.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        return semesterRespository.save(semester);
    }
}
