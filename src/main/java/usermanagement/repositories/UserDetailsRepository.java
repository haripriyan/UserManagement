package usermanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import usermanagement.models.UserDetail;


public interface UserDetailsRepository extends CrudRepository<UserDetail, Integer> {
//    UserDetail createUser(UserDetail userDetail);
}
