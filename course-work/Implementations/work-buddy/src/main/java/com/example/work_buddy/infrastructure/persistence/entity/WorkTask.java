package com.example.work_buddy.infrastructure.persistence.entity;

import com.example.work_buddy.infrastructure.persistence.entity.enums.Category;
import com.example.work_buddy.infrastructure.persistence.entity.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static org.hibernate.annotations.OnDeleteAction.SET_NULL;

@Entity
@Table(name = "work_task")
public class WorkTask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(min = 3, max = 60)
    private String title;

    @NotBlank
    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TaskStatus taskStatus;

    @DateTimeFormat
    @NotNull
    private LocalDateTime dateTime;

    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "location_address")),
            @AttributeOverride(name = "city",    column = @Column(name = "location_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "location_zip_code"))
    })
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = SET_NULL)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedWorker;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getAssignedWorker() {
        return assignedWorker;
    }

    public void setAssignedWorker(User assignedWorker) {
        this.assignedWorker = assignedWorker;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
