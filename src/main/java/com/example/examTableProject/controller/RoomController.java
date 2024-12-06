package com.example.examTableProject.controller;

import com.example.examTableProject.model.Room;
import com.example.examTableProject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public String add(@RequestBody Room room) {
        roomService.addRoom(room);
        return "Room saved";
    }

    @GetMapping("/getAll")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
