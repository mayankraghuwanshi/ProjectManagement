package com.mayank.ProjectManagement.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "Please add project summary.")
    private String summary;
    @NotBlank(message = "Please add acceptance criteria")
    private String acceptanceCriteria;
    @Column(updatable = false)
    private String projectIdentifier;
    @NotBlank(message = "please add status")
    private String status;
    @NotNull(message = "project should have priority")
    private Integer priority;
    @JsonFormat(pattern = "yyyy/mm/dd")
    @NotBlank(message = "due message is needed")
    private Date dueDate;
    @JsonFormat(pattern = "yyyy/mm/dd")
    @Column(updatable = false)
    private Date createdAt;
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    public void onUdate(){
        this.updatedAt=new Date();
    }

    public ProjectTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
