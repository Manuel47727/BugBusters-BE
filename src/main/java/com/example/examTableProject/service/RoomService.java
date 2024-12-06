package com.example.examTableProject.service;

import com.example.examTableProject.model.Room;

import java.util.List;

public interface RoomService {

    public Room addRoom(Room room);
    public List<Room> getAllRooms();
}
