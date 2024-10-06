package mk.iwec.csvreader.contact.service;

import mk.iwec.csvreader.contact.model.Contact;
import mk.iwec.csvreader.contact.observer.ContactObserver;
import mk.iwec.csvreader.infrastructure.service.GenericCSVReader;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactService extends GenericCSVReader<Contact> {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
    private static final List<String> EXPECTED_HEADERS = Arrays.asList("firstName", "lastName", "email");

    @Autowired
    public ContactService(ContactObserver contactObserver) {
        super(Contact.class);
        addObserver(contactObserver);
    }

    @Override
    protected Contact mapCsvRecordToEntity(CSVRecord csvRecord) {
        return new Contact(csvRecord.get("firstName"), csvRecord.get("lastName"), csvRecord.get("email"));
    }

    public List<Contact> readContacts(String filePath) throws IOException {
        logger.info("Reading contacts from file: {}", filePath);
        return readCsv(filePath, EXPECTED_HEADERS);
    }
}
