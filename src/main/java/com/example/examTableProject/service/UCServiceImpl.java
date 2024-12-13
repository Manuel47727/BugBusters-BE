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
        // Validação dos campos obrigatórios
        validateUC(uc);
        return ucRepository.save(uc);
    }

    @Override
    public UC updateUC(UC uc) {
        // Validação dos campos obrigatórios
        validateUC(uc);
        return ucRepository.save(uc);
    }

    private void validateUC(UC uc) {
        if (uc.getCourseId() <= 0) {
            throw new IllegalArgumentException("Course ID must be greater than 0");
        }
        if (uc.getName() == null || uc.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("UC name cannot be null or empty");
        }
        if (uc.getAno() <= 0) {
            throw new IllegalArgumentException("Year (ano) must be greater than 0");
        }
        if (uc.getSemestre() <= 0) {
            throw new IllegalArgumentException("Semester (semestre) must be greater than 0");
        }
        if (uc.getTipo() == null || uc.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo (type) cannot be null or empty");
        }
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
    public List<UC> getUCsForCurrentSemesterAndCourse(int courseId) {
        LocalDate today = LocalDate.now();
        Optional<Semester> currentSemesterOpt = semesterRepository.findAll().stream()
                .filter(semester -> !today.isBefore(semester.getStartDate()) && !today.isAfter(semester.getEndDate()))
                .findFirst();

        if (currentSemesterOpt.isEmpty()) {
            System.out.println("No current semester found for today's date.");
            return List.of();
        }

        Semester currentSemester = currentSemesterOpt.get();
        return ucRepository.findAll().stream()
                .filter(uc -> uc.getSemestre() == currentSemester.getNumSemester() && uc.getCourseId() == courseId)
                .collect(Collectors.toList());
    }

    @Override
    public void closeUC(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));

        // Validação do peso total das avaliações
        int totalWeight = evaluationRepository.findByUcId(ucId).stream()
                .mapToInt(Evaluation::getWeight)
                .sum();

        if (totalWeight != 100) {
            throw new RuntimeException("Total weight of evaluations must be 100% to close the UC.");
        }

        // Fechar a UC
        uc.setUCClosed(true);
        ucRepository.save(uc);
    }

    public boolean validateEvaluationWeights(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        double totalWeight = evaluationRepository.findByUcId(ucId).stream()
                .mapToDouble(Evaluation::getWeight)
                .sum();
        return totalWeight == 100.0;
    }

    @Override
    public void openUC(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        uc.setUCClosed(false);
        ucRepository.save(uc);
    }

    @Override
    public boolean isUCClosed(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        return uc.isUCClosed();
    }

    @Override
    public void deleteEvaluationsByUCId(int ucId) {
        List<Evaluation> evaluations = evaluationRepository.findByUcId(ucId);
        if (!evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations);
        }
    }

    @Transactional
    @Override
    public void deleteUC(int ucId) {
        deleteEvaluationsByUCId(ucId);
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        ucRepository.delete(uc);
    }
}
