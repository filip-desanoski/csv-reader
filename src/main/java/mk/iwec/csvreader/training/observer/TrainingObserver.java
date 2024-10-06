package mk.iwec.csvreader.training.observer;

import mk.iwec.csvreader.infrastructure.observer.CSVReaderObserver;
import mk.iwec.csvreader.training.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingObserver implements CSVReaderObserver {

    private static final Logger logger = LoggerFactory.getLogger(TrainingObserver.class);

    @Override
    public void update(List<?> records) {
        logger.info("TrainingObserver notified. Processed {} trainings.", records.size());

        records.stream()
                .map(record -> (Training) record)
                .forEach(training -> logger.info("Training: {} {}", training.getName(), training.getInstructor()));
    }
}
