package com.mayank.ProjectManagement.Controllers;
import com.mayank.ProjectManagement.Services.ErrorResponse;
import com.mayank.ProjectManagement.Services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mayank.ProjectManagement.Entities.ProjectTask;

import javax.validation.Valid;


@Controller
@RequestMapping("/api/backlog")
public class BacklogController {
    @Autowired
    private ErrorResponse errorResponse;

    @Autowired
    private ProjectTaskService projectTaskService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("work",HttpStatus.OK);
    }

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addProjectTask(@PathVariable String projectIdentifier , @Valid ProjectTask projectTask  , BindingResult result){
        if(result.hasErrors()) return errorResponse.mapErrorToResponse(result);
        return new ResponseEntity<>(projectTaskService.addOrUpdate(projectIdentifier,projectTask) , HttpStatus.OK);
    }


    @GetMapping("/{backlog_id}/{project_task_sequence}")
    public ResponseEntity getProjectTaskBySequence(@PathVariable String backlog_id , @PathVariable String project_task_sequence){
        ProjectTask projectTask = projectTaskService.getProjectByProjectSequence(backlog_id , project_task_sequence);
        return new ResponseEntity(projectTask , HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity getBacklogWithBacklogId(@PathVariable String backlog_id){
        return new ResponseEntity(projectTaskService.getProjectTaskWithId(backlog_id),HttpStatus.OK);
    }



}
