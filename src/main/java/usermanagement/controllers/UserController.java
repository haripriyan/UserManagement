package usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usermanagement.exceptions.UserAlreadyExistsException;
import usermanagement.models.UserDetail;
import usermanagement.requests.CreateUserRequest;
import usermanagement.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDetail> createUser(@Valid @RequestBody CreateUserRequest request) {
        try {
            return ResponseEntity.ok(service.create(new UserDetail(request)));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDetail>> retrieveUsers() {
        return ResponseEntity.ok(service.retrieveAll());
    }
}
