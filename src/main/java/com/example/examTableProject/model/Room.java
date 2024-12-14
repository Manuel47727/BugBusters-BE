package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String room_num_name;
    private String designation;
    private String type;
    private int capacity;
    private String location;

    public Room() {
    }

    /**
     * Returns the id of the room.
     *
     * @return the id of the room
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the room.
     *
     * @param id the id to set for the room
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the room number or name of the room.
     *
     * @return the room number or name of the room
     */
    public String getRoomNumName() {
        return room_num_name;
    }

    /**
     * Sets the room number or name of the room.
     *
     * @param room_num_name the room number or name of the room
     */
    public void setRoomNumName(String room_num_name) {
        this.room_num_name = room_num_name;
    }

    /**
     * Returns the designation of the room.
     *
     * @return the designation of the room
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the designation of the room.
     *
     * @param designation the designation to set for the room
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the type of room.
     *
     * @return the type of room
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the room.
     *
     * @param type the type to set for the room
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the capacity of the room.
     *
     * @return the capacity of the room
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the room.
     *
     * @param capacity the capacity of the room to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the location of the room.
     *
     * @return the location of the room
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the room.
     *
     * @param location the location of the room to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
