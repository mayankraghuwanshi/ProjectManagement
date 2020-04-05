package com.mayank.ProjectManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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
