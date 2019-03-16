package usermanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usermanagement.exceptions.UserAlreadyExistsException;
import usermanagement.models.UserDetail;
import usermanagement.repositories.UserDetailsRepository;

import java.util.List;

@Service
public class UserService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetail create(UserDetail userDetail) throws UserAlreadyExistsException {
        if(userDetailsRepository.existsById(userDetail.getName()))
            throw new UserAlreadyExistsException();
        return userDetailsRepository.save(userDetail);
    }

    public List<UserDetail> retrieveAll() {
        return (List<UserDetail>) userDetailsRepository.findAll();
    }
}
