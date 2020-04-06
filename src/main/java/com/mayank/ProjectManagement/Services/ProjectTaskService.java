package com.mayank.ProjectManagement.Services;

import com.mayank.ProjectManagement.Entities.Backlog;
import com.mayank.ProjectManagement.Entities.Project;
import com.mayank.ProjectManagement.Entities.ProjectTask;
import com.mayank.ProjectManagement.Repositories.BacklogRepository;
import com.mayank.ProjectManagement.Repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addOrUpdate(String projectIdentifier , ProjectTask projectTask){
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        Integer PTSequence = backlog.getPTSqeuence();
        PTSequence++;
        backlog.setPTSqeuence(PTSequence);
        backlogRepository.save(backlog);
        projectTask.setProjectSequence(projectIdentifier+"-"+PTSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        if(projectTask.getPriority()==null){
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus() == null){
            projectTask.setStatus("TODO");
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> getProjectTaskWithId(String projectIdentifier){
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    public ProjectTask getProjectByProjectSequence(String backlog_id , String projectSequence){

        return projectTaskRepository.findByProjectSequence(projectSequence);
    }





}
