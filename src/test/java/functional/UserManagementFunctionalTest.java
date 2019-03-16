package functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import usermanagement.UserManagementApplication;
import usermanagement.models.UserDetail;
import usermanagement.requests.CreateUserRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserManagementApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagementFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    public void shouldCreateUser_whenValidValuesArePassed() {
        CreateUserRequest request = new CreateUserRequest("Hari", "hari@yopmail.com", "password123");
        ResponseEntity<UserDetail> responseEntity = createUser(request);
        UserDetail actualUserDetail = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualUserDetail.getName()).isEqualTo(request.getName());
        assertThat(actualUserDetail.getEmail()).isEqualTo(request.getEmail());
    }

    @Test
    @DirtiesContext
    public void shouldReturn409_whenUserNameAlreadyExists() {
        CreateUserRequest request = new CreateUserRequest("Hari", "hari@yopmail.com", "password123");
        createUser(request);
        ResponseEntity<UserDetail> responseEntity = createUser(request);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void shouldReturn400_whenRequestIsInValid() {
        CreateUserRequest request = new CreateUserRequest(null, "hari@yopmail.com", "password123");
        ResponseEntity<UserDetail> responseEntity = createUser(request);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldListAllUsers_whenUserListIsRequested() {
        CreateUserRequest user1 = new CreateUserRequest("Bruce", "batman@yopmail.com", "password123");
        CreateUserRequest user2 = new CreateUserRequest("Wayne", "wayne@yopmail.com", "password123");
        createUser(user1);
        createUser(user2);
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/api/users", List.class);
        List<UserDetail> users = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(users.size()).isEqualTo(2);
    }

    private ResponseEntity<UserDetail> createUser(CreateUserRequest user2) {
        return restTemplate.postForEntity("/api/users", user2, UserDetail.class);
    }
}

