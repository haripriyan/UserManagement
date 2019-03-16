package usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanagement.models.UserDetail;
import usermanagement.repositories.UserDetailsRepository;

@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetail create(UserDetail userDetail) {
        return userDetailsRepository.save(userDetail);
    }
}
