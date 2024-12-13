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
    private EvaluationRepository evaluationRepository;

    @Override
    public Room addRoom(Room room) {
        if (room.getRoomNumName() == null || room.getRoomNumName().trim().isEmpty()) {
            throw new IllegalArgumentException("Room number/name cannot be empty");
        }
        if (room.getDesignation() == null || room.getDesignation().trim().isEmpty()) {
            throw new IllegalArgumentException("Room designation cannot be empty");
        }
        if (room.getType() == null || room.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be empty");
        }
        if (room.getCapacity() <= 0) {
            throw new IllegalArgumentException("Room capacity must be greater than 0");
        }
        if (room.getLocation() == null || room.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Room location cannot be empty");
        }

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
        List<Room> eligibleRooms = roomRepository.findByCapacityAndType(studentNum, needComputer ? "Computadores" : null);

        List<Integer> occupiedRoomIds = evaluationRepository.findOccupiedRoomsByTime(examTime);

        return eligibleRooms.stream()
                .filter(room -> !occupiedRoomIds.contains(room.getId()))
                .toList();
    }
}
