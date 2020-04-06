package com.mayank.ProjectManagement;

import com.mayank.ProjectManagement.Entities.Project;
import com.mayank.ProjectManagement.Repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}


//	//way to dump data when server loads up.
//	@Bean
//	public CommandLineRunner demoData(ProjectRepository repo) {
//		return args -> {
//			Project project = new Project();
//			project.setProjectName("Dumped project");
//			project.setProjectIdentifier("1xyz");
//			project.setDescription("no description");
//			repo.save(project);
//		};
//	}
}
