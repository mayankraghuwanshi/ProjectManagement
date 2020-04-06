package com.mayank.ProjectManagement.Services;
import com.mayank.ProjectManagement.Entities.Backlog;
import com.mayank.ProjectManagement.Entities.Project;
import com.mayank.ProjectManagement.Exceptions.ProjectIdException;
import com.mayank.ProjectManagement.Repositories.BacklogRepository;
import com.mayank.ProjectManagement.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdate(Project project){
        Project projectResponse;
        try{
            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProjectIdentifier(project.getProjectIdentifier());
                backlog.setProject(project);
            }else{
                Backlog backlog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier());
                project.setBacklog(backlog);
            }
            projectResponse = projectRepository.save(project);
        }
        catch(Exception ex){
           throw new ProjectIdException("Project with project identifier "+project.getProjectIdentifier()+" already exist!");
        }
        return projectResponse;
    }

    public Iterable<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectByIt(String projectId){
        Project project = projectRepository.findProjectByProjectIdentifier(projectId);
        if(project==null) throw new ProjectIdException("Project with id "+projectId+" doesn't exist");
        return project;
    }


    public void deleteProjectById(String projectId) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectId);
        if(project==null) throw new ProjectIdException("Project doesn't exist!");
        projectRepository.delete(project);
    }


    public void seedProject(List<Project> projectList){
        for(Project project : projectList){
            this.saveOrUpdate(project);
        }
    }
}
