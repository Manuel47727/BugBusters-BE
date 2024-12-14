package com.example.examTableProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ucId;
    private String type;
    private int weight;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private int roomId;
    private boolean needComputer;
    private int studentNum;

    public Evaluation() {
    }

    /**
     * Get the ID of the evaluation.
     *
     * @return The ID of the evaluation.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the evaluation.
     *
     * @param id The new ID of the evaluation.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the ID of the unit of curriculum (UC) associated with the evaluation.
     *
     * @return The ID of the UC.
     */
    public int getUcId() {
        return ucId;
    }

    /**
     * Set the ID of the unit of curriculum (UC) associated with the evaluation.
     *
     * @param ucId The new ID of the UC.
     */
    public void setUcId(int ucId) {
        this.ucId = ucId;
    }

    /**
     * Get the type of evaluation.
     *
     * @return The type of evaluation.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the evaluation.
     *
     * @param type The new type of evaluation.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the weight of the evaluation.
     *
     * @return The weight of the evaluation, as an integer from 0 to 100.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set the weight of the evaluation.
     *
     * @param weight The new weight of the evaluation, as an integer from 0 to 100.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Get the date and time of the evaluation.
     *
     * @return The date and time of the evaluation, as a LocalDateTime object.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Set the date and time of the evaluation.
     *
     * @param date The new date and time of the evaluation, as a LocalDateTime object.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Get the ID of the room associated with the evaluation.
     *
     * @return The ID of the room.
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * Set the ID of the room associated with the evaluation.
     *
     * @param roomId The new ID of the room.
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Determine if the evaluation needs computers.
     *
     * @return True if the evaluation requires computers, false otherwise.
     */
    public boolean isNeedComputer() {
        return needComputer;
    }

    /**
     * Set whether the evaluation requires computers.
     *
     * @param needComputer True if the evaluation requires computers, false otherwise.
     */
    public void setNeedComputer(boolean needComputer) {
        this.needComputer = needComputer;
    }

    /**
     * Get the number of students associated with the evaluation.
     *
     * @return The number of students associated with the evaluation.
     */
    public int getStudentNum() {
        return studentNum;
    }

    /**
     * Set the number of students associated with the evaluation.
     *
     * @param studentNum The new number of students associated with the evaluation.
     */
    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    /**
     * Convert the evaluation to a string.
     *
     * <p>This will return a string that looks like this:</p>
     *
     * <pre>
     * Evaluation{
     *     id=1,
     *     ucId=1,
     *     type='some type',
     *     weight=50,
     *     date=2019-01-15T12:00:00,
     *     roomId=1,
     *     needComputer=false,
     *     studentNum=15
     * }
     * </pre>
     *
     * @return A string representation of the evaluation.
     */
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", ucId=" + ucId +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", date=" + date +
                ", roomId=" + roomId +
                ", needComputer=" + needComputer +
                ", studentNum=" + studentNum +
                '}';
    }
}
