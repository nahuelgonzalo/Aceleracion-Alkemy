package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Where(clause="deleted=0")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "create_at",nullable = false)
    private Date createAt;

    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @PrePersist
    public void prePersistDate(){
        this.createAt = new Date();
    }

}
