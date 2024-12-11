package com.example.examTableProject.service;

import com.example.examTableProject.model.Room;
import com.example.examTableProject.repository.EvaluationRepository;
import com.example.examTableProject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EvaluationRepository evaluationRepository; // Add evaluation repository to check conflicts

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomNameNum(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public List<Room> getAvailableRooms(LocalDateTime examTime, int studentNum, boolean needComputer) {
        // Get all rooms that satisfy capacity and computer requirements
        List<Room> eligibleRooms = roomRepository.findByCapacityAndType(studentNum, needComputer ? "Computadores" : null);

        // Get room IDs that are already booked for the specified exam time
        List<Integer> occupiedRoomIds = evaluationRepository.findOccupiedRoomsByTime(examTime);

        // Filter out occupied rooms
        return eligibleRooms.stream()
                .filter(room -> !occupiedRoomIds.contains(room.getId()))
                .toList();
    }
}

