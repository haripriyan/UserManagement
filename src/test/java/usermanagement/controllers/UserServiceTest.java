package usermanagement.controllers;

import org.junit.Before;
import org.junit.Test;
import usermanagement.exceptions.UserAlreadyExistsException;
import usermanagement.models.UserDetail;
import usermanagement.repositories.UserDetailsRepository;
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
    public void shouldCreateUserLoginDetails_whenCreatingNewUser() throws Exception {
        UserDetail userDetail = new UserDetail("Hari", "hari@yopmail.com", "password123", new Date(1552745055166l));
        when(mockUserDetailsRepo.existsById("Hari")).thenReturn(false);
        subject.create(userDetail);
        verify(mockUserDetailsRepo, times(1)).save(any(UserDetail.class));
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowIllegalArgumentException_whenCreatingNewUserWithExistingUserName() throws Exception {
        UserDetail userDetail = new UserDetail("Hari", "hari@yopmail.com", "password123", new Date(1552745055166l));
        when(mockUserDetailsRepo.existsById("Hari")).thenReturn(true);
        subject.create(userDetail);
    }

    @Test
    public void shouldReturnAllUserLoginDetails_whenRetrievingAll() {
        subject.retrieveAll();
        verify(mockUserDetailsRepo, times(1)).findAll();
    }
}
