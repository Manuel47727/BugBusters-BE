package com.example.examTableProject.controller;

import com.example.examTableProject.model.Room;
import com.example.examTableProject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Add a new room.
     *
     * @param room The room to be added.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Room room) {
        if (room.getRoomNumName() == null || room.getRoomNumName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Room number/name is required.\"}");
        }
        if (room.getDesignation() == null || room.getDesignation().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Designation is required.\"}");
        }
        if (room.getCapacity() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Capacity must be greater than 0.\"}");
        }

        try {
            roomService.addRoom(room);
            return ResponseEntity.ok("{\"message\":\"Room saved successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error saving room: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Get all rooms.
     *
     * @return List of all rooms.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Room>> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Get a specific room by its ID.
     *
     * @param id The ID of the room.
     * @return The room information.
     */
    @GetMapping("/get")
    public ResponseEntity<Room> getRoom(@RequestParam int id) {
        try {
            Room room = roomService.getRoomNameNum(id);
            if (room == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Get available rooms for a given exam time and requirements.
     *
     * @param examTime The time of the exam.
     * @param studentNum The number of students.
     * @param needComputer Whether computers are needed.
     * @return A list of available rooms.
     */
    @GetMapping("/getAvailableRooms")
    public ResponseEntity<List<Room>> getAvailableRooms(
            @RequestParam LocalDateTime examTime,
            @RequestParam int studentNum,
            @RequestParam boolean needComputer) {
        try {
            List<Room> availableRooms = roomService.getAvailableRooms(examTime, studentNum, needComputer);
            if (availableRooms.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(availableRooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
