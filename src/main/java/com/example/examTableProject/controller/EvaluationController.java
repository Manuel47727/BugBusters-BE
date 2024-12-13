package com.example.examTableProject.controller;

import com.example.examTableProject.model.Evaluation;
import com.example.examTableProject.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * Save evaluations for a specific UC (Unit of Curriculum).
     * It first deletes the existing evaluations for the given UC and then adds the new ones.
     *
     * @param evaluations List of evaluations to be saved.
     * @param ucId UC identifier to associate evaluations.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/save")
    public ResponseEntity<String> add(@RequestBody List<Evaluation> evaluations, @RequestParam int ucId) {
        if (ucId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Invalid UC ID\"}");
        }
        if (evaluations == null || evaluations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Evaluations list cannot be empty\"}");
        }

        try {
            evaluationService.deleteEvaluationsForUC(ucId);

            for (Evaluation evaluation : evaluations) {
                evaluationService.addEvaluation(evaluation);
            }

            return ResponseEntity.ok("{\"message\":\"Evaluation saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Failed to save evaluation: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Get evaluations for a specific UC (Unit of Curriculum).
     *
     * @param ucId UC identifier to fetch evaluations for.
     * @return List of evaluations associated with the given UC.
     */
    @GetMapping("/get")
    public ResponseEntity<List<Evaluation>> getEvaluationsForUC(@RequestParam int ucId) {
        if (ucId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsForUC(ucId);
            if (evaluations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Get all evaluations across all UCs.
     *
     * @return List of all evaluations.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        try {
            List<Evaluation> evaluations = evaluationService.getAllEvaluations();
            return ResponseEntity.ok(evaluations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
