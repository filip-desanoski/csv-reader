package mk.iwec.csvreader.training.service;

import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import mk.iwec.csvreader.training.model.Training;
import mk.iwec.csvreader.training.observer.TrainingObserver;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TrainingService extends GenericCSVReader<Training> {

    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);
    private static final List<String> EXPECTED_HEADERS = Arrays.asList("name", "instructor");

    @Autowired
    public TrainingService(TrainingObserver trainingObserver) {
        super(Training.class);
        addObserver(trainingObserver);
    }

    @Override
    protected Training mapCsvRecordToEntity(CSVRecord csvRecord) {
        return new Training(csvRecord.get("name"), csvRecord.get("instructor"));
    }

    public List<Training> readTrainings(String filePath) throws IOException {
        logger.info("Reading trainings from file: {}", filePath);
        return readCsv(filePath, EXPECTED_HEADERS);
    }
}
