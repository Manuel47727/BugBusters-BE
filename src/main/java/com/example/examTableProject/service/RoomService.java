package com.example.examTableProject.service;

import com.example.examTableProject.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {

    public Room addRoom(Room room);
    public List<Room> getAllRooms();
    public List<Room> getAvailableRooms(LocalDateTime examTime, int studentNum, boolean needComputer);

}
