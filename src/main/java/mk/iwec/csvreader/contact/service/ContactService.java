package mk.iwec.csvreader.contact.service;

import mk.iwec.csvreader.contact.model.Contact;
import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ContactService extends GenericCSVReader<Contact> {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    public ContactService() {
        super(Contact.class);
    }

    @Override
    protected Contact mapCsvRecordToEntity(CSVRecord csvRecord) {
        String firstName = csvRecord.get("firstName");
        String lastName = csvRecord.get("lastName");
        String email = csvRecord.get("email");
        return new Contact(firstName, lastName, email);
    }

    public List<Contact> readContacts(String filePath) throws IOException {
        logger.info("Reading contacts from file: {}", filePath);
        return readCsv(filePath);
    }
}
