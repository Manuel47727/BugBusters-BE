package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numSemester;
    private LocalDate startDate;
    private LocalDate endDate;


    public Semester() {
    }

    /**
     * Retrieves the ID of the semester.
     *
     * @return the ID of the semester
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the semester.
     *
     * @param id the ID of the semester
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the number of the semester.
     *
     * @return the number of the semester
     */
    public int getNumSemester() {
        return numSemester;
    }

    /**
     * Sets the number of the semester.
     *
     * @param numSemester the number of the semester
     */
    public void setNumSemester(int numSemester) {
        this.numSemester = numSemester;
    }

    /**
     * Retrieves the start date of the semester.
     *
     * @return the start date of the semester
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the semester.
     *
     * @param startDate the start date of the semester
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the semester.
     *
     * @return the end date of the semester
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the semester.
     *
     * @param endDate the end date of the semester
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
