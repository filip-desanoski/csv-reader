package mk.iwec.csvreader.user.service;

import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import mk.iwec.csvreader.user.model.User;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService extends GenericCSVReader<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        super(User.class);
    }

    @Override
    protected User mapCsvRecordToEntity(CSVRecord csvRecord) {
        String username = csvRecord.get("username");
        String password = csvRecord.get("password");
        String role = csvRecord.get("role");
        return new User(username, password, role);
    }

    public List<User> readUsers(String filePath) throws IOException {
        logger.info("Reading users from file: {}", filePath);
        return readCsv(filePath);
    }
}
