package com.mayank.ProjectManagement.Repositories;


import com.mayank.ProjectManagement.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    User getUserById(long id);
}
