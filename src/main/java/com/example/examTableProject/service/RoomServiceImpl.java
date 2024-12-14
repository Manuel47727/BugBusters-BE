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

    /**
     * Saves a new room to the database.
     *
     * @param room The room to be added.
     * @return The saved room.
     * @throws IllegalArgumentException if any of the room fields are invalid.
     */
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

    /**
     * Retrieves all rooms from the repository.
     *
     * @return A list of all rooms available in the repository.
     */
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    /**
     * Retrieves a room by its unique identifier.
     *
     * @param id The unique identifier of the room.
     * @return The room with the specified id, or null if no such room exists.
     */
    @Override
    public Room getRoomNameNum(int id) {
        return roomRepository.findById(id).orElse(null);
    }

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
    @Override
    public List<Room> getAvailableRooms(LocalDateTime examTime, int studentNum, boolean needComputer) {
        List<Room> eligibleRooms = roomRepository.findByCapacityAndType(studentNum, needComputer ? "Computadores" : null);

        List<Integer> occupiedRoomIds = evaluationRepository.findOccupiedRoomsByTime(examTime);

        return eligibleRooms.stream()
                .filter(room -> !occupiedRoomIds.contains(room.getId()))
                .toList();
    }
}
