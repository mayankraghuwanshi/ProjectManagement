package com.mayank.ProjectManagement.Repositories;
import com.mayank.ProjectManagement.Entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project , Long> {
    public Project findProjectByProjectIdentifier(String projectId);
    public void deleteProjectByProjectIdentifier(String projectId);

}
