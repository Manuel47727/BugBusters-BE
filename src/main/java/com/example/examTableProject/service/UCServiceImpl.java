package com.example.examTableProject.service;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.model.Semester;
import com.example.examTableProject.repository.EvaluationRepository;
import com.example.examTableProject.repository.UCRepository;
import com.example.examTableProject.repository.SemesterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.examTableProject.model.Evaluation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public UC addUC(UC uc) {
        return ucRepository.save(uc);
    }

    @Override
    public List<UC> getAllUCs() {
        return ucRepository.findAll();
    }

    @Override
    public UC getUC(int ucId) {
        return ucRepository.findById(ucId).orElse(null);
    }

    @Override
    public UC updateUC(UC uc) {
        ucRepository.save(uc); // Updates if the entity exists, inserts otherwise
        return uc;
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

    @Override
    public void closeUC(int ucId) {
        // Find the UC by ID
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));

        // Check if evaluations for the UC have a total weight of 100%
        int totalWeight = evaluationRepository.findByUcId(ucId).stream()
                .mapToInt(Evaluation::getWeight)
                .sum();

        if (totalWeight != 100) {
            throw new RuntimeException("Total weight of evaluations must be 100% to close the UC.");
        }

        // Close the UC
        uc.setUCClosed(true);
        ucRepository.save(uc);
    }

    public boolean validateEvaluationWeights(int ucId) {
        // Fetch the UC and its associated evaluations
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));

        // Get the total weight from the evaluations associated with the UC
        double totalWeight = evaluationRepository.findByUcId(ucId).stream()
                .mapToDouble(Evaluation::getWeight)  // Assuming Evaluation has a getWeight method
                .sum();

        // Check if the total weight is 100%
        return totalWeight == 100.0;
    }

    @Override
    public void openUC(int ucId) {
        // Find the UC by ID, update isClosed to false, and save it
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        uc.setUCClosed(false);
        ucRepository.save(uc);
    }

    @Override
    public boolean isUCClosed(int ucId) {
        // Find the UC by ID and return isClosed
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        return uc.isUCClosed();
    }

    @Override
    public void deleteEvaluationsByUCId(int ucId) {
        // Delete all evaluations associated with the UC
        List<Evaluation> evaluations = evaluationRepository.findByUcId(ucId);
        if (!evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations);
            System.out.println("Evaluations deleted for UC with id: " + ucId);
        }
    }

    @Transactional
    @Override
    public void deleteUC(int ucId) {
        // Delete all evaluations first
        deleteEvaluationsByUCId(ucId);

        // Then delete the UC
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        ucRepository.delete(uc);
        System.out.println("UC with id " + ucId + " has been deleted.");
    }
}
