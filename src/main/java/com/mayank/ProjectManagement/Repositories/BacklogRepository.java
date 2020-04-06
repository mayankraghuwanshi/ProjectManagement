package com.mayank.ProjectManagement.Repositories;

import com.mayank.ProjectManagement.Entities.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog , Long> {
    Backlog findByProjectIdentifier(String projectIdentifier);
}
