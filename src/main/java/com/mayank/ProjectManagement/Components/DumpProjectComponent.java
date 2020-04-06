package com.mayank.ProjectManagement.Components;


import com.mayank.ProjectManagement.Entities.Project;
import com.mayank.ProjectManagement.Repositories.ProjectRepository;
import com.mayank.ProjectManagement.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DumpProjectComponent {
    @Autowired
    ProjectService projectService;

    @EventListener
    public void dumpProject(ApplicationReadyEvent event){
        List<Project> projectList  = new ArrayList<>(Arrays.asList(
                new Project("First Project Dumped" , "1xyz" , "No description"),
                new Project("Second Project Dumped" , "2xyz" , "No description"),
                new Project("Third" , "3xyz" , "No description")
        ));

        projectService.seedProject(projectList);
    }
}
