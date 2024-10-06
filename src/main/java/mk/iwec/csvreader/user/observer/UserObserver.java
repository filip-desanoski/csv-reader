package mk.iwec.csvreader.user.observer;

import mk.iwec.csvreader.infrastructure.observer.CSVReaderObserver;
import mk.iwec.csvreader.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserObserver implements CSVReaderObserver {

    private static final Logger logger = LoggerFactory.getLogger(UserObserver.class);

    @Override
    public void update(List<?> records) {
        logger.info("UserObserver notified. Processed {} users.", records.size());

        records.stream()
                .map(record -> (User) record)
                .forEach(user -> logger.info("Contact: {} {} {}", user.getUsername(), user.getPassword(), user.getRole()));
    }
}
