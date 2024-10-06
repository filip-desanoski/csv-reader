package mk.iwec.csvreader.user.service;

import mk.iwec.csvreader.contact.model.Contact;
import mk.iwec.csvreader.contact.observer.ContactObserver;
import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import mk.iwec.csvreader.user.model.User;
import mk.iwec.csvreader.user.observer.UserObserver;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService extends GenericCSVReader<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final List<String> EXPECTED_HEADERS = Arrays.asList("username", "password", "role");

    @Autowired
    public UserService(UserObserver userObserver) {
        super(User.class);
        addObserver(userObserver);
    }

    @Override
    protected User mapCsvRecordToEntity(CSVRecord csvRecord) {
        return new User(csvRecord.get("username"), csvRecord.get("password"), csvRecord.get("role"));
    }

    public List<User> readUsers(String filePath) throws IOException {
        logger.info("Reading contacts from file: {}", filePath);
        return readCsv(filePath, EXPECTED_HEADERS);
    }
}
