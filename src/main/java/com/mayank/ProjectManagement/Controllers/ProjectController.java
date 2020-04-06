package com.mayank.ProjectManagement.Controllers;
import com.mayank.ProjectManagement.Entities.Project;
import com.mayank.ProjectManagement.Services.ErrorResponse;
import com.mayank.ProjectManagement.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Controller
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ErrorResponse errorResponse;
    @Autowired
    ProjectService projectService;


    @RequestMapping("/home")
    public ResponseEntity<String> home(){
        return new ResponseEntity<String>("Home" , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid Project project , BindingResult result){
        if(result.hasErrors()) return errorResponse.mapErrorToResponse(result);
        Project projectResponse  =  projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(projectResponse , HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable String projectId){
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<String>("Success" , HttpStatus.OK);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId){
        return new ResponseEntity<Project>(projectService.getProjectByIt(projectId) , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Project>> getAllProjects(){
        return new ResponseEntity<>(projectService.getAllProjects(),HttpStatus.OK);
    }

}
