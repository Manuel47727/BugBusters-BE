package com.example.examTableProject.service;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.model.Semester;
import com.example.examTableProject.repository.UCRepository;
import com.example.examTableProject.repository.SemesterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UCServiceImpl implements UCService {

    @Autowired
    private UCRepository ucRepository;

    @Autowired
    private SemesterRespository semesterRepository;

    @Override
    public UC addUC(UC uc) {
        return ucRepository.save(uc);
    }

    @Override
    public List<UC> getAllUCs() {
        return ucRepository.findAll();
    }

    public List<UC> getUCsForCurrentSemesterAndCourse(int courseId) {
        // Step 1: Get today's date
        LocalDate today = LocalDate.now();
        System.out.println("Today's Date: " + today);

        // Step 2: Get all semesters from the database
        List<Semester> allSemesters = semesterRepository.findAll();

        // Step 3: Find the current semester by comparing today's date with the semester's start and end dates
        Optional<Semester> currentSemesterOpt = allSemesters.stream()
                .filter(semester -> !today.isBefore(semester.getStartDate()) && !today.isAfter(semester.getEndDate()))
                .findFirst();  // Get the first semester that matches today's date

//        // Step 4: If no current semester is found, return an empty list
//        if (currentSemesterOpt.isEmpty()) {
//            System.out.println("No current semester found for today's date.");
//            return List.of();  // Return empty list if no matching semester
//        }

        // Step 5: Get the semester number from the current semester
        Semester currentSemester = currentSemesterOpt.get();
        int currentSemesterNumber = currentSemester.getNumSemester();
        //System.out.println("Current Semester Number: " + currentSemesterNumber);

        // Step 6: Use the current semester number and courseId to filter UC records
        List<UC> ucsForCurrentSemesterAndCourse = ucRepository.findAll().stream()
                .filter(uc -> uc.getSemestre() == currentSemesterNumber && uc.getCourseId() == courseId)
                .collect(Collectors.toList());

        // Log the result to verify
        if (ucsForCurrentSemesterAndCourse.isEmpty()) {
            System.out.println("No UCs found for courseId: " + courseId + " and current semester.");
        } else {
            System.out.println("Found UCs: " + ucsForCurrentSemesterAndCourse.size());
        }

        // Step 7: Return the filtered UC list
        return ucsForCurrentSemesterAndCourse;
    }
}
