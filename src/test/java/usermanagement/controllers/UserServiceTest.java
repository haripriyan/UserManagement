package usermanagement.controllers;

import org.junit.Before;
import org.junit.Test;
import usermanagement.models.UserDetail;
import usermanagement.repositories.UserDetailsRepository;
import usermanagement.requests.CreateUserRequest;
import usermanagement.services.UserService;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService subject;
    private UserDetailsRepository mockUserDetailsRepo;

    @Before
    public void setUp() {
        mockUserDetailsRepo = mock(UserDetailsRepository.class);
        subject = new UserService(mockUserDetailsRepo);
    }

    @Test
    public void shouldCreateUserLoginDetails_whenCreatingNewUser() {
        UserDetail userDetail = new UserDetail("Hari", "hari@yopmail.com", "password123", new Date(1552745055166l));
        subject.create(userDetail);
        verify(mockUserDetailsRepo, times(1)).save(any(UserDetail.class));
    }
}
