package usermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usermanagement.models.UserDetail;
import usermanagement.requests.CreateUserRequest;
import usermanagement.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("users")
    public UserDetail createUser(@RequestBody CreateUserRequest request) {
        return service.create(new UserDetail(request));
    }
}
