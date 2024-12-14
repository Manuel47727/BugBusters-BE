package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int courseId;
    private String name;
    private int ano;
    private int semestre;
    private String tipo;
    private boolean mandatory;
    private boolean isUCClosed;

    public UC() {
    }

    /**
     * Gets the id of the UC.
     *
     * @return the id of the UC
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the UC.
     *
     * @param id the new id for the UC
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the course ID that this UC belongs to.
     *
     * @return the course ID
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Sets the course ID that this UC belongs to.
     *
     * @param courseId the new course ID for the UC
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Gets the name of the UC.
     *
     * @return the name of the UC
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the UC.
     *
     * @param name the new name for the UC
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the year of the UC.
     *
     * @return the year of the UC
     */
    public int getAno() {
        return ano;
    }

    /**
     * Sets the year of the UC.
     *
     * @param ano the new year
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Returns the semester of the UC.
     *
     * @return the semester of the UC
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * Sets the semester of the UC.
     *
     * @param semestre the new semester
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Returns the type of the UC.
     *
     * @return the type of the UC
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the type of the UC.
     *
     * @param tipo the new type to set for the UC
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets whether the UC is mandatory.
     *
     * @return true if the UC is mandatory, false otherwise
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets whether the UC is mandatory.
     *
     * @param mandatory true if the UC is mandatory, false otherwise
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Checks if the UC is closed.
     *
     * @return true if the UC is closed, false otherwise
     */

    public boolean isUCClosed() {
        return isUCClosed;
    }

    /**
     * Sets the status of whether the UC is closed.
     *
     * @param isUCClosed the new closed status of the UC
     */
    public void setUCClosed(boolean isUCClosed) {
        this.isUCClosed = isUCClosed;
    }
}
