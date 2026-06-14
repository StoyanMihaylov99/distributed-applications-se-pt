package com.example.work_buddy.infrastructure.persistence.entity;

import com.example.work_buddy.infrastructure.persistence.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Size(min = 10, max = 10)
    @Column(unique = true)
    private String phoneNumber;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime modified;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Review> reviewsAsCustomer;

    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private List<Review> reviewsAsWorker;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<WorkTask> tasksAsCustomer;

    @OneToMany(mappedBy = "assignedWorker", fetch = FetchType.LAZY)
    private List<WorkTask> tasksAsWorker;

    @OneToMany(mappedBy = "postOwner", fetch = FetchType.LAZY)
    private List<ServicePost> servicePosts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Review> getReviewsAsCustomer() {
        return reviewsAsCustomer;
    }

    public void setReviewsAsCustomer(List<Review> reviewsAsCustomer) {
        this.reviewsAsCustomer = reviewsAsCustomer;
    }

    public List<Review> getReviewsAsWorker() {
        return reviewsAsWorker;
    }

    public void setReviewsAsWorker(List<Review> reviewsAsWorker) {
        this.reviewsAsWorker = reviewsAsWorker;
    }

    public List<WorkTask> getTasksAsCustomer() {
        return tasksAsCustomer;
    }

    public void setTasksAsCustomer(List<WorkTask> tasksAsCustomer) {
        this.tasksAsCustomer = tasksAsCustomer;
    }

    public List<WorkTask> getTasksAsWorker() {
        return tasksAsWorker;
    }

    public void setTasksAsWorker(List<WorkTask> tasksAsWorker) {
        this.tasksAsWorker = tasksAsWorker;
    }

    public List<ServicePost> getServicePosts() {
        return servicePosts;
    }

    public void setServicePosts(List<ServicePost> servicePosts) {
        this.servicePosts = servicePosts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
