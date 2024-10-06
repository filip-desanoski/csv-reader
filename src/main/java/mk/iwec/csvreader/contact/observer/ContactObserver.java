package mk.iwec.csvreader.contact.observer;

import mk.iwec.csvreader.contact.model.Contact;
import mk.iwec.csvreader.infrastructure.observer.CSVReaderObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactObserver implements CSVReaderObserver {

    private static final Logger logger = LoggerFactory.getLogger(ContactObserver.class);

    @Override
    public void update(List<?> records) {
        logger.info("ContactObserver notified. Processed {} contacts.", records.size());

        records.stream()
                .map(record -> (Contact) record)
                .forEach(contact -> logger.info("Contact: {} {}", contact.getFirstName(), contact.getLastName()));
    }
}
