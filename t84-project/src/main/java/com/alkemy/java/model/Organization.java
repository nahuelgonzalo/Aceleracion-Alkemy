package com.alkemy.java.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "organization")
@Data
public class Organization implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String image;

    private String address;

    private Integer phone;

    private String facebookUrl;

    private String linkedinUrl;

    private String instagramUrl;
    
    @Column(nullable = false)
    private String email;
    
    @Column(name = "welcome_text",nullable = false,columnDefinition = "TEXT")
    private String welcomeText;
    
    @Column(name = "about_us_text",nullable = false,columnDefinition = "TEXT")
    private String aboutUsText;
    
    private boolean deleted;
    
    @Column(name = "create_at")
    private Date createAt;
    
    @PrePersist
    public void prePersistDate(){
        this.createAt = new Date();
    }
}
