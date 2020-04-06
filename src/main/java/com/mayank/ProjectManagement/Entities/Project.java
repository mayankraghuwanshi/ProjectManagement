package com.mayank.ProjectManagement.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.security.PublicKey;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide project name")
    private String projectName;
    @NotBlank(message = "Please provide project identifier")

    @Column(unique = true , updatable = false)
    private String projectIdentifier;
    @NotBlank(message = "Please provide description")
    private String description;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date createDate;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date updateDate;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date startDate;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;


    public Project(){};
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @PrePersist
    private void onCreate(){
        this.createDate = new Date();
    }

    @PreUpdate
    private void onUpdate(){
        this.updateDate = new Date();
    }

    public Project(String projectName , String projectIdentifier , String description){
        this.projectName=projectName;
        this.projectIdentifier=projectIdentifier;
        this.description=description;
    }
}
