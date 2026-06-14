package com.example.work_buddy.infrastructure.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static org.hibernate.annotations.OnDeleteAction.SET_NULL;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(min = 3, max = 60)
    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 1000)
    private String description;

    @Min(1) @Max(5)
    @NotNull
    private Double rating;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = SET_NULL)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = SET_NULL)
    private User worker;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = SET_NULL)
    private WorkTask workTask;

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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public WorkTask getWorkTask() {
        return workTask;
    }

    public void setWorkTask(WorkTask workTask) {
        this.workTask = workTask;
    }
}
