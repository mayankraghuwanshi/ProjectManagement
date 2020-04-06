package com.mayank.ProjectManagement.Services;
import com.mayank.ProjectManagement.Entities.User;
import com.mayank.ProjectManagement.Exceptions.UserAlredyExistException;
import com.mayank.ProjectManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        User userResponse;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            userResponse = userRepository.save(user);
        }
        catch (Exception e){
            throw new UserAlredyExistException("Email already exist");
        }
        return userResponse;
    }

}
