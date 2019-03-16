package usermanagement.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import usermanagement.exceptions.UserAlreadyExistsException;
import usermanagement.models.UserDetail;
import usermanagement.services.UserService;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mvc;
    private UserService mockUserService;

    @Before
    public void setUp() {
        mockUserService = mock(UserService.class);
        mvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService)).build();
    }

    @Test
    public void shouldCreateUserDetails_whenValidRequestIsPassed() throws Exception {
        when(mockUserService.create(any(UserDetail.class))).thenReturn(new UserDetail("Hari", "hari@yopmail.com", "password123",new Date(1552745055166l)));
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Hari\",\"email\":\"hari@yopmail.com\", \"password\":\"password123\"}"))
                .andExpect(status().is(200))
                .andExpect(content().string("{\"name\":\"Hari\",\"email\":\"hari@yopmail.com\",\"lastLogin\":1552745055166}"));
        verify(mockUserService, times(1)).create(any(UserDetail.class));
    }

    @Test
    public void shouldReturn409_whenUserNameAlreadyExists() throws Exception {
        when(mockUserService.create(any(UserDetail.class))).thenThrow(new UserAlreadyExistsException());
        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Hari\",\"email\":\"hari@yopmail.com\", \"password\":\"password123\"}"))
                .andExpect(status().is(409));
        verify(mockUserService, times(1)).create(any(UserDetail.class));
    }

    @Test
    public void shouldListOfUsers_whenGetUsersIsCalled() throws Exception {
        when(mockUserService.create(any(UserDetail.class))).thenReturn(new UserDetail("Hari", "hari@yopmail.com", "password123",new Date(1552745055166l)));
        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        verify(mockUserService, times(1)).retrieveAll();
    }
}
