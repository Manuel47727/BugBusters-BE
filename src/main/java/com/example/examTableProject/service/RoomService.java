package com.example.examTableProject.service;

import com.example.examTableProject.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

    /**
     * Add a new room.
     *
     * @param room The room to be added.
     * @return The room that was added.
     */
    public Room addRoom(Room room);
    /**
     * Get all rooms.
     *
     * @return List of all rooms.
     */
    public List<Room> getAllRooms();
    /**
     * Retrieves a room by its unique identifier.
     *
     * @param id The unique identifier of the room.
     * @return The room with the specified id, or null if no such room exists.
     */
    public Room getRoomNameNum(int id);
    /**
     * Finds all rooms that can accommodate a given number of students and a given
     * set of requirements (computer availability) at the given time.
     *
     * @param examTime The time at which to find the rooms.
     * @param studentNum The minimum number of students the rooms must be able
     *                   to accommodate.
     * @param needComputer Whether or not the rooms must have computers.
     * @return A list of all rooms that match the criteria, or an empty list if
     * no such rooms exist.
     */
    public List<Room> getAvailableRooms(LocalDateTime examTime, int studentNum, boolean needComputer);

}
