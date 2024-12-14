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

    /**
     * Saves a semester to the database.
     *
     * @param semester The semester to be saved.
     * @return The saved semester.
     * @throws IllegalArgumentException If the semester number is not greater than 0, or if the start date is null, or if the end date is null, or if the start date is after the end date.
     */
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

    /**
     * Retrieves all semesters from the database.
     *
     * @return A list of all semesters. The list is empty if there are no semesters.
     */
    @Override
    public List<Semester> getAllSemesters() {
        return semesterRespository.findAll();
    }

    /**
     * Retrieves a semester by ID from the database.
     *
     * @param id The ID of the semester to be retrieved.
     * @return The semester with the given ID, or null if no such semester exists.
     */
    @Override
    public Semester getSemesterById(int id) {
        return semesterRespository.findById(id).orElse(null);
    }

    /**
     * Updates a semester in the database.
     *
     * @param semester The semester with updated information.
     * @return The updated semester.
     * @throws IllegalArgumentException If the semester number is not greater than 0, or if the start date is null, or if the end date is null, or if the start date is after the end date.
     */
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
