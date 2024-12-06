package com.example.examTableProject.controller;

import com.example.examTableProject.model.Evaluation;
import com.example.examTableProject.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/add")
    public String add(@RequestBody Evaluation evaluation) {
        evaluationService.addEvaluation(evaluation);
        return "Evaluation saved";
    }

    @GetMapping("/get")
    public List<Evaluation> getEvaluationsForUC(@RequestParam int ucId) {
        return evaluationService.getEvaluationsForUC(ucId);
    }

    @GetMapping("/getAll")
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }
}
