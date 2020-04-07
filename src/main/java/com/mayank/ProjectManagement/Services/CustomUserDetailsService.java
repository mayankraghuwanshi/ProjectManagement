package com.mayank.ProjectManagement.Services;

import com.mayank.ProjectManagement.Entities.User;
import com.mayank.ProjectManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user==null) throw new UsernameNotFoundException("No user found!");
        return user;
    }

    @Transactional
    public UserDetails loadUserByUserId(long id){
        User user = userRepository.getUserById(id);
        if(user==null) throw new UsernameNotFoundException("No user found!");
        return user;
    }
}

