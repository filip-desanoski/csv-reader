package mk.iwec.csvreader.training.api;

import jakarta.validation.constraints.NotBlank;
import mk.iwec.csvreader.infrastructure.EndPoints;
import mk.iwec.csvreader.training.model.Training;
import mk.iwec.csvreader.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(EndPoints.TRAINING)
@Validated
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<Training>> readTrainings(@RequestParam @NotBlank String filePath) {
        try {
            List<Training> trainings = trainingService.readTrainings(filePath);
            return ResponseEntity.ok(trainings);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
