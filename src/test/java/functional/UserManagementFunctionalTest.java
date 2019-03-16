package functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import usermanagement.requests.CreateUserRequest;
import usermanagement.UserManagementApplication;
import usermanagement.models.UserDetail;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserManagementApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserManagementFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateUserWhenValidValuesArePassed() {
        CreateUserRequest request = new CreateUserRequest("Hari", "hari@yopmail.com", "password123");
        ResponseEntity<UserDetail> responseEntity = restTemplate.postForEntity("/api/users", request, UserDetail.class);
        UserDetail actualUserDetail = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualUserDetail.getName()).isEqualTo(request.getName());
        assertThat(actualUserDetail.getEmail()).isEqualTo(request.getEmail());
    }
}
