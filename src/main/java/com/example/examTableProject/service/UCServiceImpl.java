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

    /**
     * Adds a new Unit of Curriculum (UC) to the repository after validating
     * its required fields.
     *
     * @param uc The UC object to be added.
     * @return The saved UC object.
     * @throws IllegalArgumentException if any validation constraints are violated.
     */
    @Override
    public UC addUC(UC uc) {
        // Validação dos campos obrigatórios
        validateUC(uc);
        return ucRepository.save(uc);
    }

    /**
     * Updates an existing Unit of Curriculum (UC) in the repository after validating
     * its required fields.
     *
     * @param uc The UC object to be updated.
     * @return The updated UC object.
     * @throws IllegalArgumentException if any validation constraints are violated.
     */
    @Override
    public UC updateUC(UC uc) {
        // Validação dos campos obrigatórios
        validateUC(uc);
        return ucRepository.save(uc);
    }

    /**
     * Validates the required fields of a Unit of Curriculum (UC) object.
     * <p>
     * The following constraints are checked:
     * <ul>
     *     <li>Course ID must be greater than 0.</li>
     *     <li>UC name cannot be null or empty.</li>
     *     <li>Year (ano) must be greater than 0.</li>
     *     <li>Semester (semestre) must be greater than 0.</li>
     *     <li>Tipo (type) cannot be null or empty.</li>
     * </ul>
     * <p>
     * If any of these constraints are violated, an {@link IllegalArgumentException} is thrown.
     *
     * @param uc The UC object to be validated.
     */
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

/**
 * Retrieves a list of all Unit of Curriculum (UC) objects from the repository.
 *
 * @return A list containing all UCs.
 */
    @Override
    public List<UC> getAllUCs() {
        return ucRepository.findAll();
    }

    /**
     * Retrieves a Unit of Curriculum (UC) object from the repository by its ID.
     * <p>
     * If a UC with the given ID does not exist, this method returns {@code null}.
     *
     * @param ucId The ID of the UC to be retrieved.
     * @return The UC with the given ID, or {@code null} if it does not exist.
     */
    @Override
    public UC getUC(int ucId) {
        return ucRepository.findById(ucId).orElse(null);
    }

    /**
     * Retrieves a list of Unit of Curriculum (UC) objects for the current semester
     * and specified course.
     * <p>
     * This method determines the current semester based on today's date and
     * filters the UCs accordingly. If no current semester is found, an empty list
     * is returned.
     *
     * @param courseId The ID of the course to filter the UCs.
     * @return A list of UCs for the current semester and specified course, or an
     *         empty list if no current semester is found.
     */
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

    /**
     * Closes the specified Unit of Curriculum (UC) after ensuring the total weight of its evaluations is 100%.
     *
     * <p>This method first retrieves the UC by its ID. It calculates the total weight of all associated evaluations.
     * If the total weight is not exactly 100%, an exception is thrown. Otherwise, the UC is marked as closed and saved
     * back to the repository.</p>
     *
     * @param ucId The ID of the UC to be closed.
     * @throws RuntimeException if the UC is not found or if the total weight of evaluations is not 100%.
     */
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

    /**
     * Validates if the total weight of all evaluations for a given Unit of Curriculum (UC) equals 100%.
     *
     * <p>This method retrieves all evaluations associated with the specified UC ID
     * and calculates their total weight. If the total weight is exactly 100%, the method
     * returns true. Otherwise, it returns false.</p>
     *
     * @param ucId The ID of the UC for which to validate the evaluation weights.
     * @return True if the total weight of all evaluations is 100%, false otherwise.
     * @throws RuntimeException if the UC is not found.
     */
    public boolean validateEvaluationWeights(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        double totalWeight = evaluationRepository.findByUcId(ucId).stream()
                .mapToDouble(Evaluation::getWeight)
                .sum();
        return totalWeight == 100.0;
    }

    /**
     * Opens a previously closed unit of curriculum (UC).
     *
     * <p>This method finds the UC by its ID and marks it as open by setting its
     * {@code isUCClosed} field to false. The updated UC is then saved back to the
     * repository.</p>
     *
     * @param ucId The ID of the UC to be opened.
     * @throws RuntimeException if the UC is not found.
     */
    @Override
    public void openUC(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        uc.setUCClosed(false);
        ucRepository.save(uc);
    }

    /**
     * Checks if a given unit of curriculum (UC) is closed.
     *
     * @param ucId The ID of the UC to check.
     * @return True if the UC is closed, false otherwise.
     * @throws RuntimeException if the UC is not found.
     */
    @Override
    public boolean isUCClosed(int ucId) {
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        return uc.isUCClosed();
    }


    /**
     * Deletes all evaluations associated with the specified Unit of Curriculum (UC).
     *
     * <p>This method retrieves all evaluations linked to the given UC ID.
     * If evaluations are found, they are removed from the repository.</p>
     *
     * @param ucId The ID of the UC whose evaluations are to be deleted.
     */
    @Override
    public void deleteEvaluationsByUCId(int ucId) {
        List<Evaluation> evaluations = evaluationRepository.findByUcId(ucId);
        if (!evaluations.isEmpty()) {
            evaluationRepository.deleteAll(evaluations);
        }
    }

    /**
     * Deletes a unit of curriculum (UC) along with all its associated evaluations.
     *
     * <p>This method first deletes all evaluations linked to the given UC ID.
     * Then, it removes the UC itself from the repository. The operation is performed
     * within a transactional context to ensure data consistency.</p>
     *
     * @param ucId The ID of the UC to be deleted.
     * @throws RuntimeException if the UC is not found.
     */
    @Transactional
    @Override
    public void deleteUC(int ucId) {
        deleteEvaluationsByUCId(ucId);
        UC uc = ucRepository.findById(ucId).orElseThrow(() -> new RuntimeException("UC not found"));
        ucRepository.delete(uc);
    }
}
