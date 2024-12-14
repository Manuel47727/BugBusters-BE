package com.example.examTableProject.repository;

import com.example.examTableProject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    // Find rooms with a specific type and capacity greater than a given number
    List<Room> findByCapacityGreaterThanAndType(int capacity, String type);

    // Find rooms without a specific type and capacity greater than a given number
    List<Room> findByCapacityGreaterThanAndTypeNot(int capacity, String type);


    /**
     * Finds rooms with a capacity greater than or equal to the given number
     * and matching the given type (or any type if the given type is null).
     *
     * @param studentNum the minimum capacity of the rooms
     * @param type the type of the rooms to find, or null to find all types
     * @return a list of rooms matching the criteria
     */
    @Query("SELECT r FROM Room r WHERE r.capacity >= :studentNum AND (:type IS NULL OR r.type = :type)")
    List<Room> findByCapacityAndType(@Param("studentNum") int studentNum, @Param("type") String type);
}
