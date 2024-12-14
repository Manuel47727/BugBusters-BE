package com.example.examTableProject.service;

import com.example.examTableProject.model.Semester;

import java.util.List;

public interface SemesterService {

    /**
     * Saves a semester in the database. If the semester already exists, its data is updated.
     *
     * @param semester The semester to be saved.
     * @return The saved semester.
     */
    public Semester saveSemester(Semester semester);

    /**
     * Retrieves all semesters from the database.
     *
     * @return A list of all semesters in the database.
     */
    public List<Semester> getAllSemesters();

    /**
     * Retrieves a semester from the database by its ID.
     *
     * @param id The ID of the semester to be retrieved.
     * @return The semester with the specified ID, or null if not found.
     */
    public Semester getSemesterById(int id);

    /**
     * Updates an existing semester in the database.
     *
     * @param semester The semester object containing updated information.
     * @return The updated semester object.
     * @throws IllegalArgumentException if the provided semester has invalid data.
     */
    public Semester updateSemester(Semester semester);
}
