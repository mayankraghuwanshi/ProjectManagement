package com.mayank.ProjectManagement.Entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String projectName;
    private String projectIdentity;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Date startDate;

    public long getId() {
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

    public String getProjectIdentity() {
        return projectIdentity;
    }

    public void setProjectIdentity(String projectIdentity) {
        this.projectIdentity = projectIdentity;
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


}
