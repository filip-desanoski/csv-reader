package mk.iwec.csvreader.training.service;

import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import mk.iwec.csvreader.training.model.Training;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TrainingService extends GenericCSVReader<Training> {

    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

    public TrainingService() {
        super(Training.class);
    }

    @Override
    protected Training mapCsvRecordToEntity(CSVRecord csvRecord) {
        String name = csvRecord.get("name");
        String instructor = csvRecord.get("instructor");
        return new Training(name, instructor);
    }

    public List<Training> readTrainings(String filePath) throws IOException {
        logger.info("Reading trainings from file: {}", filePath);
        return readCsv(filePath);
    }
}
