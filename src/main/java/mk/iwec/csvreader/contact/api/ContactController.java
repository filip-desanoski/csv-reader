package mk.iwec.csvreader.contact.api;

import jakarta.validation.constraints.NotBlank;
import mk.iwec.csvreader.contact.model.Contact;
import mk.iwec.csvreader.contact.service.ContactService;
import mk.iwec.csvreader.infrastructure.EndPoints;
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
@RequestMapping(EndPoints.CONTACT)
@Validated
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<Contact>> readContacts(@RequestParam @NotBlank String filePath) {
        try {
            List<Contact> contacts = contactService.readContacts(filePath);
            return ResponseEntity.ok(contacts);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
