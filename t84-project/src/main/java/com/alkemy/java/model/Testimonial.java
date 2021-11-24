package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String image;
    private String content;
    @CreationTimestamp
    @NotNull
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    private boolean deleted = false;
    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}
