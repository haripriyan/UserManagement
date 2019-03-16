package usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usermanagement.exceptions.UserAlreadyExistsException;
import usermanagement.models.UserDetail;
import usermanagement.requests.CreateUserRequest;
import usermanagement.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("users")
    public ResponseEntity<UserDetail> createUser(@Valid @RequestBody CreateUserRequest request) {
        try {
            return ResponseEntity.ok(service.create(new UserDetail(request)));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
