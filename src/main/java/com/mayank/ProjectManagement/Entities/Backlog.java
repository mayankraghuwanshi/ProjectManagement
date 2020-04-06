package com.mayank.ProjectManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer PTSqeuence = 0;
    @Column(updatable = false)
    private String projectIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id" , nullable = false)
    @JsonIgnore
    private Project project;

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL, mappedBy = "backlog")
    private List<ProjectTask> projectTasks;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSqeuence() {
        return PTSqeuence;
    }

    public void setPTSqeuence(Integer PTSqeuence) {
        this.PTSqeuence = PTSqeuence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Backlog() {
    }
}
