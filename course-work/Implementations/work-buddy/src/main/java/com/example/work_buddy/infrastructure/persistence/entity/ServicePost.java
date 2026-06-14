package com.example.work_buddy.infrastructure.persistence.entity;

import com.example.work_buddy.infrastructure.persistence.entity.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.annotations.OnDeleteAction.SET_NULL;

@Entity
@Table(name = "service_posts")
public class ServicePost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Size(min = 3, max = 60)
    private String title;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @NotBlank
    @Column(length = 1000)
    private String description;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    private BigDecimal pricePerHour;

    @Embedded
    @NotNull
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "location_address")),
            @AttributeOverride(name = "city",    column = @Column(name = "location_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "location_zip_code"))
    })
    @OnDelete(action = SET_NULL)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = SET_NULL)
    private User postOwner;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
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
