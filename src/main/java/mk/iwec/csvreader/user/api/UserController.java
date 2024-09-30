package mk.iwec.csvreader.user.api;

import jakarta.validation.constraints.NotBlank;
import mk.iwec.csvreader.infrastructure.EndPoints;
import mk.iwec.csvreader.user.model.User;
import mk.iwec.csvreader.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(EndPoints.USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<User>> readUsers(@RequestParam @NotBlank String filePath) {
        try {
            List<User> users = userService.readUsers(filePath);
            return ResponseEntity.ok(users);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
